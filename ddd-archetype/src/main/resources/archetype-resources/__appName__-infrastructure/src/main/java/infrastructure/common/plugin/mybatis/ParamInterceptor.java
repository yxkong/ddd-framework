#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.mybatis;

import ${package}.infrastructure.common.util.LoginTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * insert or update 前给入参中的tenantId、createTime、updateTime赋值
 *
 * @author ${author}
 * @date 2021/7/2-11:03
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts({
        // @Signature(type = ParameterHandler.class, method = "setParameters", args = PreparedStatement.class),
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class})/*,
        @Signature(type = Executor.class, method = "createCacheKey", args = {MappedStatement.class, Object.class, RowBounds.class, BoundSql.class})*/
}
)
public class ParamInterceptor implements Interceptor {

    private static final String TENANT_ID = "tenantId";
    private static final String CREATE_TIME = "createTime";
    private static final String UPDATE_TIME = "updateTime";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        // 拦截 Executor 的 update 方法 生成sql前将 tenantId 设置到实体中
        if (invocation.getTarget() instanceof Executor && invocation.getArgs().length == 2) {
            return invokeUpdate(invocation);
        }
        // 拦截 Executor 的 createCacheKey 方法，pageHelper插件会拦截 query 方法，调用此方法，提前将参数设置到参数集合中
        /*if (invocation.getTarget() instanceof Executor && invocation.getArgs().length == 4) {
            return invokeCacheKey(invocation);
        }*/
        //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
        /*if (invocation.getTarget() instanceof ParameterHandler) {
            return invokeSetParameter(invocation);
        }*/
        return null;
    }

    private Object invokeCacheKey(Invocation invocation) throws Exception {
        Executor executor = (Executor) invocation.getTarget();
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        if (ms.getSqlCommandType() != SqlCommandType.SELECT/* && ms.getSqlCommandType() != SqlCommandType.DELETE*/) {
            return invocation.proceed();
        }

        Object parameterObject = invocation.getArgs()[1];
        RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
        BoundSql boundSql = (BoundSql) invocation.getArgs()[3];

        List<String> paramNames = new ArrayList<>();
        boolean hasKey = hasParamKey(paramNames, boundSql.getParameterMappings());

        if (!hasKey) {
            return invocation.proceed();
        }
        // 改写参数
        parameterObject = processSingle(parameterObject, paramNames);

        return executor.createCacheKey(ms, parameterObject, rowBounds, boundSql);
    }

    private Object invokeUpdate(Invocation invocation) throws Exception {
        Executor executor = (Executor) invocation.getTarget();
        // 获取第一个参数
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        // 非 insert和update 语句 不处理
        if (ms.getSqlCommandType() != SqlCommandType.INSERT && ms.getSqlCommandType() != SqlCommandType.UPDATE) {
            return invocation.proceed();
        }
        // mybatis的参数对象
        Object paramObj = invocation.getArgs()[1];
        if (paramObj == null) {
            return invocation.proceed();
        }

        // 更新语句只传一个基本类型参数, 不做处理
        if (ClassUtils.isPrimitiveOrWrapper(paramObj.getClass())
                || String.class.isAssignableFrom(paramObj.getClass())
                || Number.class.isAssignableFrom(paramObj.getClass())) {
            return invocation.proceed();
        }

        processParam(paramObj, ms);
        return executor.update(ms, paramObj);
    }


    /*private Object invokeSetParameter(Invocation invocation) throws Exception {

        ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
        PreparedStatement ps = (PreparedStatement) invocation.getArgs()[0];

        // 反射获取 BoundSql 对象，此对象包含生成的sql和sql的参数map映射
        Field boundSqlField = parameterHandler.getClass().getDeclaredField("boundSql");
        boundSqlField.setAccessible(true);
        BoundSql boundSql = (BoundSql) boundSqlField.get(parameterHandler);

        List<String> paramNames = new ArrayList<>();
        // 若参数映射没有包含的key直接返回
        boolean hasKey = hasParamKey(paramNames, boundSql.getParameterMappings());
        if (!hasKey) {
            return invocation.proceed();
        }

        // 反射获取 参数对像
        Field parameterField = parameterHandler.getClass().getDeclaredField("parameterObject");
        parameterField.setAccessible(true);
        Object parameterObject = parameterField.get(parameterHandler);

        // 改写参数
        parameterObject = processSingle(parameterObject, paramNames);

        // 改写的参数设置到原parameterHandler对象
        parameterField.set(parameterHandler, parameterObject);
        parameterHandler.setParameters(ps);
        return null;
    }*/

    // 判断已生成sql参数映射中是否包含tenantId
    private boolean hasParamKey(List<String> paramNames, List<ParameterMapping> parameterMappings) {
        boolean hasKey = false;
        for (ParameterMapping parameterMapping : parameterMappings) {
            if (StringUtils.equals(parameterMapping.getProperty(), TENANT_ID)) {
                hasKey = true;
            } else {
                paramNames.add(parameterMapping.getProperty());
            }
        }
        return hasKey;
    }

    private Object processSingle(Object paramObj, List<String> paramNames) throws Exception {

        Map<String, Object> paramMap = new MapperMethod.ParamMap<>();
        if (paramObj == null) {
            paramMap.put(TENANT_ID, LoginTokenUtil.getTenantId());
            paramObj = paramMap;
            // 单参数 将 参数转为 map
        } else if (ClassUtils.isPrimitiveOrWrapper(paramObj.getClass())
                || String.class.isAssignableFrom(paramObj.getClass())
                || Number.class.isAssignableFrom(paramObj.getClass())) {
            if (paramNames.size() == 1) {
                paramMap.put(paramNames.iterator().next(), paramObj);
                paramMap.put(TENANT_ID, LoginTokenUtil.getTenantId());
                paramObj = paramMap;
            }
        } else {
            fillTenantId(paramObj);
        }
        return paramObj;
    }

    private void fillTenantId(Object paramObj) throws IllegalAccessException, InvocationTargetException {
        if (paramObj instanceof Map) {
            Map originParamMap = ((Map) paramObj);
            originParamMap.putIfAbsent(TENANT_ID, LoginTokenUtil.getTenantId());
        } else {
            fillField(paramObj, TENANT_ID, LoginTokenUtil.getTenantId());
        }
    }

    private void processParam(Object parameterObject, MappedStatement ms) throws IllegalAccessException, InvocationTargetException {
        // 处理参数对象  如果是 map 且map的key 中没有 tenantId，添加到参数map中
        // 如果参数是bean，反射设置值
        Date date = new Date();
        if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
            if (parameterObject instanceof Map) {
                Map paramMap = ((Map) parameterObject);
                paramMap.putIfAbsent(CREATE_TIME, date);
                paramMap.putIfAbsent(UPDATE_TIME, date);
                paramMap.putIfAbsent(TENANT_ID, LoginTokenUtil.getTenantId());
            } else {
                fillField(parameterObject, CREATE_TIME, date);
                fillField(parameterObject, UPDATE_TIME, date);
                fillField(parameterObject, TENANT_ID, LoginTokenUtil.getTenantId());
            }
        } else if (ms.getSqlCommandType() == SqlCommandType.UPDATE) {
            if (parameterObject instanceof Map) {
                Map paramMap = ((Map) parameterObject);
                paramMap.putIfAbsent(UPDATE_TIME, date);
                paramMap.putIfAbsent(TENANT_ID, LoginTokenUtil.getTenantId());
            } else {
                fillField(parameterObject, UPDATE_TIME, date);
                fillField(parameterObject, TENANT_ID, LoginTokenUtil.getTenantId());
            }
        } /*else if(ms.getSqlCommandType() == SqlCommandType.DELETE) {
            fillTenantId(parameterObject);
        }*/
    }

    private void fillField(Object parameterObject, String property, Object value) throws IllegalAccessException, InvocationTargetException {
        PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(parameterObject.getClass(), property);
        if (ps != null && ps.getReadMethod() != null && ps.getWriteMethod() != null) {
            Object originValue = ps.getReadMethod().invoke(parameterObject);
            if (originValue == null) {
                ps.getWriteMethod().invoke(parameterObject, value);
            }
        }
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
