package com.yxkong.common.exception;

/**
 * 参数异常
 * @Author: yxkong
 * @Date: 2021/4/5 7:49 下午
 * @version: 1.0
 */
public class ParamsRuntimeException extends RuntimeException{
    public ParamsRuntimeException() {
    }

    public ParamsRuntimeException(String message) {
        super(message);
    }

    public ParamsRuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamsRuntimeException(Throwable cause) {
        super(cause);
    }

    public ParamsRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}