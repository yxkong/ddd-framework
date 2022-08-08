package com.yxkong.common.exception;

import com.yxkong.common.constant.ResultStatusEnum;

/**
 * 没有数据操作权限异常
 * @Author: yxkong
 * @Date: 2022/6/18 12:19 PM
 * @version: 1.0
 */
public class NoAuthForDataOptException extends CommonException {
    public NoAuthForDataOptException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public NoAuthForDataOptException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public NoAuthForDataOptException(String status, String message) {
        super(status, message);
    }
}
