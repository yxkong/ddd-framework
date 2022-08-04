package com.yxkong.common.exception;

import com.arbitration.common.constant.ResultStatusEnum;

/**
 * 基础设施层异常
 *
 * @Author: yxkong
 * @Date: 2021/11/15 2:56 PM
 * @version: 1.0
 */
public class InfrastructureException extends com.arbitration.common.exception.CommonException {
    public InfrastructureException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public InfrastructureException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public InfrastructureException(String status,String message) {
        super(status, message);
    }
}
