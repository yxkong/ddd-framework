package com.yxkong.demo.application.exception;

import com.yxkong.common.exception.BaseResult;

/**
 * 应用层异常枚举，应用层异常以3开头
 *
 * @Author: yxkong
 * @Date: 2021/11/15 3:05 PM
 * @version: 1.0
 */
public enum ApplicationExceptionEnum implements BaseResult {

    NOT_FOUD_SLIDINGBLOCK_SUPPLIER("3001", "未实现该供应商的滑块功能");

    ApplicationExceptionEnum(String status, String message) {
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
