package com.yxkong.common.exception;

import com.arbitration.common.constant.ResultStatusEnum;

/**
 * 配置异常异常
 * @Author: yxkong
 * @Date: 2021/4/5 7:49 下午
 * @version: 1.0
 */
public class ConfigRuntimeException extends com.arbitration.common.exception.CommonException {
    public ConfigRuntimeException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public ConfigRuntimeException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public ConfigRuntimeException(String status, String message) {
        super(status, message);
    }
}