package com.yxkong.common.exception;


import com.yxkong.common.constant.ResultStatusEnum;

/**
 * 全局拦截异常
 * @Author: yxkong
 * @Date: 2022/6/15
 * @version: 1.0
 */
public class GlobalException extends CommonException {
    public GlobalException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public GlobalException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public GlobalException(String status, String message) {
        super(status, message);
    }
}
