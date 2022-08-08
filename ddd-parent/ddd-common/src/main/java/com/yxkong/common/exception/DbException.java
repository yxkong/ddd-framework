package com.yxkong.common.exception;

import com.yxkong.common.constant.ResultStatusEnum;

/**
 * 数据库操作异常
 *
 * @author yxkong
 * @date 2019-05-27 20:07
 */
public class DbException extends CommonException {

    private static final long serialVersionUID = 8016028387771340304L;

    public DbException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public DbException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
    public DbException(String status,String message) {
        super(status, message);
    }
}
