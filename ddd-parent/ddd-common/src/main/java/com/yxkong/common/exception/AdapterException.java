package com.yxkong.common.exception;

import com.yxkong.common.constant.ResultStatusEnum;

/**
 * 适配层异常
 *
 * @Author: yxkong
 * @Date: 2021/11/15 2:55 PM
 * @version: 1.0
 */
public class AdapterException extends CommonException{
    public AdapterException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public AdapterException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public AdapterException(String status,String message) {
        super(status, message);
    }
}
