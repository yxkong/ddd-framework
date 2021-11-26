package com.yxkong.demo.domain.dto.context;

import com.yxkong.demo.domain.model.user.PwdObject;
import com.yxkong.demo.domain.model.user.UserObject;
import lombok.Builder;
import lombok.Getter;

/**
 * 登录上下文
 *
 * @Author: yxkong
 * @Date: 2021/11/26 2:20 PM
 * @version: 1.0
 */
@Builder
@Getter
public class LoginContext {
    private UserObject userObject;
    private String requestIp;
    private String  pwd;
    /**
     * 1密码登录
     */
    private Integer loginType;
    private String proId;
    private String env;

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
}
