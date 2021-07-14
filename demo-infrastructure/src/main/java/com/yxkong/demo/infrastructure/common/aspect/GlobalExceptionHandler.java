package com.yxkong.demo.infrastructure.common.aspect;

import com.yxkong.common.constant.HeaderConstant;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.exception.BizException;
import com.yxkong.common.exception.FeignCallException;
import com.yxkong.demo.infrastructure.common.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 */
@RestControllerAdvice
@Order(-1)
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResultBean<?> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e,
                                                                HttpServletRequest request) {
        logger.error("HttpRequestMethodNotSupportedException,ip:{};uri:{},当前请求方法:{};支持的请求方法:{},异常信息:{}",
                WebUtil.getIpAddress(request), request.getRequestURI(), request.getMethod(), Arrays.toString(e.getSupportedMethods()), e);
        return new ResultBean.Builder<>().fail(e.getMessage()).build();
    }

    /**
     * 必传参数没有传递
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResultBean<?> missingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("MissingServletRequestParameterException:", e);
        String parameterName = e.getParameterName();
        return new ResultBean.Builder<>().fail().message(String.format("参数[%s]必填", parameterName)).build();
    }

    /**
     * 数据校验失败捕获
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResultBean<?> methodArgumentNotValidHandler(MethodArgumentNotValidException e) {
        //logger.error("MethodArgumentNotValidException:", e);
        StringBuilder sb = new StringBuilder();
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            String format = String.format("[%s: %s]", error.getField(), error.getDefaultMessage());
            sb.append(format);
        }
        return new ResultBean.Builder<>().fail().message(sb.toString()).build();
    }

    /**
     * spring 绑定参数异常捕获
     */
    @ExceptionHandler(BindException.class)
    public ResultBean<?> bindException(BindException e) {
        logger.error("BindException:", e);
        StringBuilder sb = new StringBuilder();
        // 解析原错误信息，封装后返回，此处返回非法的字段名称，错误信息
        if (e.hasErrors()) {
            List<FieldError> errorList = e.getFieldErrors();
            errorList.forEach(fieldError -> {
                sb.append(String.format("[%s] %s;", fieldError.getField(), fieldError.getDefaultMessage()));
            });
        }
        return new ResultBean.Builder<>().fail().message(sb.toString()).build();
    }

    /**
     * 请求内容类型如果不是json，或者为json单请求内容为空，会捕获该异常
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResultBean<?> httpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("HttpMessageNotReadableException:", e);
        return new ResultBean.Builder<>().fail().message("请求内容类型应为json，请求内容不能为空！").build();
    }

    /**
     * 响应内容解析json失败
     */
    @ExceptionHandler(HttpMessageNotWritableException.class)
    public ResultBean<?> httpMessageNotWritableException(HttpMessageNotWritableException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},响应内容转json失败:", tokenKey, e);
        return new ResultBean.Builder<>().fail().message("响应内容转json失败！").build();
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BizException.class)
    public ResultBean<?> bizException(BizException e) {
        return new ResultBean.Builder<>().status(e.getStatus()).message(e.getMessage()).build();
    }



    /**
     * 拦截Feign调用异常
     */
    @ExceptionHandler(FeignCallException.class)
    public ResultBean<?> feignException(FeignCallException e) {
        logger.error("Feign调用异常:", e);
        return new ResultBean.Builder<>().status(e.getStatus()).message(e.getMessage()).build();
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultBean<?> notFount(RuntimeException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的运行时异常:", tokenKey, e);
        return new ResultBean.Builder<>().status("500").message("系统异常，请稍后重试！").build();
    }
}
