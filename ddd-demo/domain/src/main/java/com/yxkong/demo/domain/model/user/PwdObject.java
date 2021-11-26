package com.yxkong.demo.domain.model.user;

import com.yxkong.common.annotation.DomainValueObject;
import lombok.Getter;

/**
 * 密码值对象
 *
 * @Author: yxkong
 * @Date: 2021/11/26 10:22 AM
 * @version: 1.0
 */
@DomainValueObject
@Getter
public class PwdObject {
    private String salt;
    private String md5Pwd;

    public PwdObject(String salt, String md5Pwd) {
        this.salt = salt;
        this.md5Pwd = md5Pwd;
    }
}
