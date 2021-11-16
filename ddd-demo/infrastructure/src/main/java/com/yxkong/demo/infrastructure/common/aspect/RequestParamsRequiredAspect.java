package com.yxkong.demo.infrastructure.common.aspect;

import com.yxkong.common.exception.InfrastructureException;
import com.yxkong.common.util.ResultBeanUtil;
import com.yxkong.demo.infrastructure.common.annotation.RequiredRealName;
import com.yxkong.demo.infrastructure.common.annotation.RequiredToken;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import com.yxkong.demo.infrastructure.common.util.JsonUtils;
import com.yxkong.common.constant.ResultStatusEnum;
import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.exception.BizExceptionEnum;
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
 * @Author: yxkong
 * @Date: 2021/6/10 10:16 上午
 * @version: 1.0
 */
@Aspect
@Component
@Order(-900)
public class RequestParamsRequiredAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestParamsRequiredAspect.class);

    @Around("execution(* com.yxkong..controller.*.*(..))")
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
                    return ResultBeanUtil.result(ResultStatusEnum.TOKEN_INVALID);
                }
            }
            RequiredRealName requiredRealName = targetMethod.getAnnotation(RequiredRealName.class);
            if (Objects.nonNull(requiredRealName)) {
                if (Objects.isNull(loginToken)) {
                    loginToken = LoginTokenUtil.getLoginToken();
                }
                if (!loginToken.isRealName()) {
                    return ResultBeanUtil.result(BizExceptionEnum.NO_REAL_NAME);
                }
            }
        } catch (Exception e) {
            logger.error("请求类:" + className + ",method=" + methodName + " ,loginToken=" + JsonUtils.toJson(loginToken), e);
            return new InfrastructureException(ResultStatusEnum.ERROR) ;

        }
        Object[] objects = jp.getArgs();
        return jp.proceed(objects);
    }
}
