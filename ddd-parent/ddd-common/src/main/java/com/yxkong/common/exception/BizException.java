package com.yxkong.common.exception;


import com.yxkong.common.constant.ResultStatusEnum;

/**
 * @author yxkong
 * @date 2019-05-27 19:46
 */
public class BizException extends CommonException {

    private static final long serialVersionUID = -8928701617617894128L;

    public BizException(BaseResult exceptionResult) {
        super(exceptionResult.getStatus(), exceptionResult.getMessage());
    }

    public BizException(ResultStatusEnum resultStatusEnum) {
        super(resultStatusEnum.getStatus(), resultStatusEnum.getMessage());
    }
}
