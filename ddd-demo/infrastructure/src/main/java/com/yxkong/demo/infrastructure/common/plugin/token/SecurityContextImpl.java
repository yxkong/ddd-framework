package com.yxkong.demo.infrastructure.common.plugin.token;

import com.yxkong.common.entity.common.LoginToken;

/**
 * @Author: yxkong
 * @Date: 2021/6/3 11:30 上午
 * @version: 1.0
 */
public class SecurityContextImpl implements SecurityContext{
    private LoginToken loginToken;

    public SecurityContextImpl(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public SecurityContextImpl() {
    }

    @Override
    public LoginToken getLoinToken() {
        return this.loginToken;
    }
    public void setLoginToken(LoginToken loginToken){
        this.loginToken = loginToken;
    }
}