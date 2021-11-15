package com.yxkong.common.exception;

import com.yxkong.common.constant.ResultStatusEnum;

/**
 * 应用层异常
 *
 * @Author: yxkong
 * @Date: 2021/11/15 2:54 PM
 * @version: 1.0
 */
public class ApplicationException extends CommonException{
    public ApplicationException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public ApplicationException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public ApplicationException(String status,String message) {
        super(status, message);
    }
}
