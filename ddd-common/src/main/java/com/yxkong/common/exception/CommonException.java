package com.yxkong.common.exception;

/**
 * 通用异常
 *
 * @Author: yxk
 * @date 2019/5/27-19:41
 */
public class CommonException extends RuntimeException {

    private static final long serialVersionUID = -2706753300347526671L;

    private String status;

    private String message;

    protected CommonException() {

    }

    protected CommonException(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
