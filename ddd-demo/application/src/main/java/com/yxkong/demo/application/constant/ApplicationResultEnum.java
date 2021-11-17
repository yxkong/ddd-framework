package com.yxkong.demo.application.constant;

import com.yxkong.common.exception.BaseResult;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/17 3:27 PM
 * @version: 1.0
 */
public enum ApplicationResultEnum implements BaseResult {
    ;

    ApplicationResultEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    private String status;
    private String message;

    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
