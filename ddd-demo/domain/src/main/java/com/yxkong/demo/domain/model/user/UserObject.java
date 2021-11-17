package com.yxkong.demo.domain.model.user;

import com.yxkong.common.annotation.DomainValueObject;
import com.yxkong.common.constant.TenantEnum;
import lombok.Getter;

/**
 * 未注册之前的用户值对象
 *
 * @Author: yxkong
 * @Date: 2021/11/15 3:19 PM
 * @version: 1.0
 */
@DomainValueObject
@Getter
public class UserObject {
    /**
     * 用户手机号码
     */
    private String mobile;
    private TenantEnum tenantEnum;


    public UserObject(String mobile, TenantEnum tenantEnum) {
        this.mobile = mobile;
        this.tenantEnum = tenantEnum;
    }
}
