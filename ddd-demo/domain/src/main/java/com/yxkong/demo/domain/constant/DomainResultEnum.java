package com.yxkong.demo.domain.constant;

import com.yxkong.common.exception.BaseResult;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/17 3:15 PM
 * @version: 1.0
 */
public enum DomainResultEnum implements BaseResult {
    REGISTERED("2001","您已注册，请登录！")
    ;

    DomainResultEnum(String status, String message) {
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
