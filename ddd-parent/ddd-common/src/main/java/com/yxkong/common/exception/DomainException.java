package com.yxkong.common.exception;

import com.arbitration.common.constant.ResultStatusEnum;

/**
 * 领域层异常
 *
 * @Author: yxkong
 * @Date: 2021/11/15 2:55 PM
 * @version: 1.0
 */
public class DomainException extends CommonException{
    public DomainException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public DomainException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public DomainException(String status,String message) {
        super(status, message);
    }
}
