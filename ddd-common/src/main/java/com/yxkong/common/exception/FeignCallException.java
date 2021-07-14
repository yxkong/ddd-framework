package com.yxkong.common.exception;
import com.yxkong.common.entity.dto.ResultBean;

/**
 * Feign 调用异常
 *
 * @Author: yxk
 * @date 2019-05-27 20:07
 */
public class FeignCallException extends CommonException {

    private static final long serialVersionUID = -1887241576634384486L;

    public FeignCallException(ResultBean<?> resultBean) {
        super(resultBean.getStatus(), resultBean.getMessage());
    }
}
