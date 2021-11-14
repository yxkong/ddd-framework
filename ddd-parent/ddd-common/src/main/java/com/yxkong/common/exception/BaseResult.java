package com.yxkong.common.exception;

/**
 * @author yxkong
 * @date 2019/5/27-19:24
 */
public interface BaseResult {

    /**
     * 获取异常编码
     */
    String getStatus();

    /**
     * 获取异常信息
     */
    String getMessage();
}
