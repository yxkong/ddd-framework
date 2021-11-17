package com.yxkong.demo.infrastructure.common.wrapper;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/17 10:47 AM
 * @version: 1.0
 */
public class HttpRequestWrapper {
    public static HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
