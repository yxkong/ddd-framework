package com.yxkong.demo.domain.constant;

import com.yxkong.common.exception.BaseResult;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/17 3:15 PM
 * @version: 1.0
 */
public enum DomainResultEnum implements BaseResult {
    //这个比较特殊，主要是为了屏蔽后端信息
    REGISTERED("1","您已注册，请登录！"),
    REGISTERFAIL("2002","注册异常，请稍后再试！")
    ;

    DomainResultEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    private String status;
    private String message;

    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
