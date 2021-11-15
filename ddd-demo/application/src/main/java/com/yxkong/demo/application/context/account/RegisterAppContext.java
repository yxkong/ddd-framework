package com.yxkong.demo.application.context.account;

import com.yxkong.demo.domain.constant.TenantEnum;
import com.yxkong.demo.domain.model.user.UserObject;
import lombok.Getter;

/**
 * 注册上下文
 *
 * @Author: yxkong
 * @Date: 2021/11/15 6:45 PM
 * @version: 1.0
 */
@Getter
public class RegisterAppContext {
    private UserObject user;
    private String  verifyCode;
    private String pwd;
    private Integer registerType;
    private String requestIp;

    public RegisterAppContext(Integer tenantId, String mobile, String verifyCode, String requestIp, String pwd){
        TenantEnum tenantEnum = TenantEnum.get(tenantId);
        this.verifyCode = verifyCode;
        this.user = new UserObject(mobile,tenantEnum);
        this.requestIp = requestIp;
        this.pwd = pwd;
    }

}
