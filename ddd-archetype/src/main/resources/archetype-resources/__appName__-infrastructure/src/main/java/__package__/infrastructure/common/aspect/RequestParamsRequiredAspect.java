#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.aspect;

import ${package}.infrastructure.common.annotation.RequiredRealName;
import ${package}.infrastructure.common.annotation.RequiredToken;
import ${package}.infrastructure.common.util.LoginTokenUtil;
import ${package}.infrastructure.common.util.JsonUtils;
import ${groupId}.common.constant.ResultStatusEnum;
import ${groupId}.common.entity.common.LoginToken;
import ${groupId}.common.entity.dto.ResultBean;
import ${groupId}.common.exception.BizExceptionEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 请求参数校验
 * 除了自定义注解，后续参数的通用校验也放这
 *
 * @Author: ${author}
 * @Date: 2021/6/10 10:16 上午
 * @version: ${version}
 */
@Aspect
@Component
@Order(-900)
public class RequestParamsRequiredAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestParamsRequiredAspect.class);

    @Around("execution(* ${groupId}.${appName}..controller.*.*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        Method targetMethod = ((MethodSignature) (jp.getSignature())).getMethod();
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        LoginToken loginToken = null;
        try {
            RequiredToken requiredToken = targetMethod.getAnnotation(RequiredToken.class);
            if (Objects.nonNull(requiredToken)) {
                loginToken = LoginTokenUtil.getLoginToken();
                if (!loginToken.isValid()) {
                    return new ResultBean.Builder<>().statusEnum(ResultStatusEnum.TOKEN_INVALID).build();
                }
            }
            RequiredRealName requiredRealName = targetMethod.getAnnotation(RequiredRealName.class);
            if (Objects.nonNull(requiredRealName)) {
                if (Objects.isNull(loginToken)) {
                    loginToken = LoginTokenUtil.getLoginToken();
                }
                if (!loginToken.isRealName()) {
                    return new ResultBean.Builder<>().statusEnum(BizExceptionEnum.NO_REAL_NAME).build();
                }
            }
        } catch (Exception e) {
            logger.error("请求类:" + className + ",method=" + methodName + " ,loginToken=" + JsonUtils.toJson(loginToken), e);
            return new ResultBean.Builder<>().fail().message("系统异常，请稍后再试！").build();

        }
        Object[] objects = jp.getArgs();
        return jp.proceed(objects);
    }
}