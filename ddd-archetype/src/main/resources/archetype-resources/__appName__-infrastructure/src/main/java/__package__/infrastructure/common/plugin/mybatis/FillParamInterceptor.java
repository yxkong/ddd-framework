#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.mybatis;

import ${package}.infrastructure.common.util.LoginTokenUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.util.ClassUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 查询时增加查询参数（tenantId）
 *
 * @Author: ${author}
 * @date 2020/8/1-21:26
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(
        {
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
                @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
        }
)
public class FillParamInterceptor implements Interceptor {

    private static final String TENANT_ID = "tenantId";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        if (ms.getSqlCommandType() != SqlCommandType.SELECT) {
            return invocation.proceed();
        }
        Object parameter = args[1];
        // 如果类型是map，提前处理，要不ms.getBoundSql(parameter)会报错
        parameter = processParamAnnotation(parameter);
        BoundSql boundSql;
        //由于逻辑关系，只会进入一次
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
        } else {
            //6 个参数时
            boundSql = (BoundSql) args[5];
        }
        // 改写参数
        parameter = processParam(parameter, boundSql);
        args[1] = parameter;
        return invocation.proceed();
    }

    private Object processParam(Object paramObj, BoundSql boundSql) throws Exception {

        Map<String, Object> paramMap = new MapperMethod.ParamMap<>();
        if (ClassUtils.isPrimitiveOrWrapper(paramObj.getClass())
                || String.class.isAssignableFrom(paramObj.getClass())
                || Number.class.isAssignableFrom(paramObj.getClass())) {
            List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
            if (parameterMappings.size() == 1) {
                ParameterMapping parameterMapping = parameterMappings.get(0);
                if (!StringUtils.equals(parameterMapping.getProperty(), TENANT_ID)) {
                    paramMap.put(parameterMapping.getProperty(), paramObj);
                    paramMap.put("param1", paramObj);
                    paramMap.put(TENANT_ID, LoginTokenUtil.getTenantId());
                    paramMap.put("param2", LoginTokenUtil.getTenantId());
                    paramObj = paramMap;
                }
            }
        } else {
            fillTenantId(paramObj);
        }
        return paramObj;
    }

    private Object processParamAnnotation(Object paramObj) {

        Map<String, Object> paramMap = new MapperMethod.ParamMap<>();
        if (paramObj == null) {
            paramMap.put(TENANT_ID, LoginTokenUtil.getTenantId());
            paramMap.put("param1", LoginTokenUtil.getTenantId());
            paramObj = paramMap;
            // 单参数 将 参数转为 map
        } else if (paramObj instanceof Map) {
            Map originParamMap = ((Map) paramObj);
            if (!originParamMap.containsKey(TENANT_ID)) {
                originParamMap.put(TENANT_ID, LoginTokenUtil.getTenantId());
                // 有问题
                // originParamMap.put("param" + originParamMap.size(), LoginTokenUtil.getTenantId());
            }
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
