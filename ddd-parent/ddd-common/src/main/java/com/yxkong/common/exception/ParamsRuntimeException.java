package com.yxkong.common.exception;


import com.yxkong.common.constant.ResultStatusEnum;

/**
 * 参数异常
 * @Author: yxkong
 * @Date: 2021/4/5 7:49 下午
 * @version: 1.0
 */
public class ParamsRuntimeException extends CommonException {
    public ParamsRuntimeException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public ParamsRuntimeException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public ParamsRuntimeException(String status, String message) {
        super(status, message);
    }
}