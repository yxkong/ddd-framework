#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.aspect;

import ${groupId}.common.exception.*;
import ${groupId}.common.util.ResultBeanUtil;
import ${package}.infrastructure.common.constant.HeaderConstant;
import ${package}.infrastructure.common.util.WebUtil;
import ${groupId}.common.entity.dto.ResultBean;
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
 * @Author: ${author}
 * @date 2019/5/24-17:08
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
        return new ResultBean.Builder<>().fail(e.getMessage()).message("请求方式错误，请更换为"+Arrays.toString(e.getSupportedMethods())).build();
    }

    /**
     * 必传参数没有传递
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
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
     * 拦截数据库操作异常
     */
    @ExceptionHandler(DbException.class)
    public ResultBean<?> dbException(DbException e) {
        logger.error("数据库操作异常:", e);
        return ResultBeanUtil.result(e);
    }

    /**
     * 拦截Feign调用异常
     */
    @ExceptionHandler(FeignCallException.class)
    public ResultBean<?> feignException(FeignCallException e) {
        logger.error("Feign调用异常:", e);
        return ResultBeanUtil.result(e);
    }


    @ExceptionHandler(ParamsRuntimeException.class)
    public ResultBean<?> notFount(ParamsRuntimeException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的参数校验异常:", tokenKey, e);
        return ResultBeanUtil.fail("参数校验异常，请查验参数！",null);
    }
    @ExceptionHandler(AdapterException.class)
    public ResultBean<?> adapterException(AdapterException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的适配层异常:", tokenKey, e);
        return ResultBeanUtil.result(e);
    }
    @ExceptionHandler(ApplicationException.class)
    public ResultBean<?> applicationException(ApplicationException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的应用层异常:", tokenKey, e);
        return ResultBeanUtil.result(e);
    }
    @ExceptionHandler(DomainException.class)
    public ResultBean<?> domainException(DomainException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的领域层异常:", tokenKey, e);
        return ResultBeanUtil.result(e);
    }
    @ExceptionHandler(InfrastructureException.class)
    public ResultBean<?> ${artifactId}Exception(InfrastructureException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的基础设施层异常:", tokenKey, e);
        return ResultBeanUtil.result(e);
    }
    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public ResultBean<?> notFount(RuntimeException e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的运行时异常:", tokenKey, e);
        return ResultBeanUtil.fail();
    }
    /**
     * 终极处理
     * @param e
     * @param request
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResultBean<?> exception(Exception e, HttpServletRequest request) {
        String tokenKey = request.getHeader(HeaderConstant.TOKEN);
        logger.error("用户token:{},统一拦截到的未知异常:", tokenKey, e);
        return ResultBeanUtil.fail("未知异常",e.getMessage());
    }

}
