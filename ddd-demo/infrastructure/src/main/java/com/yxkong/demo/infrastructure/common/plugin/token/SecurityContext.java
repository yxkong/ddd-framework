package com.yxkong.demo.infrastructure.common.plugin.token;

import com.yxkong.common.entity.common.LoginToken;

/**
 * @Author: yxkong
 * @Date: 2021/6/3 11:11 上午
 * @version: 1.0
 */
public interface SecurityContext {
    /**
     * 获取用户登录信息
     * @return
     */
    LoginToken getLoinToken();

}