package com.yxkong.demo.domain.model.authtask;

import com.yxkong.common.annotation.DomainValueObject;
import lombok.Getter;

/**
 * @Author: yxkong
 * @Date: 2021/7/14
 * @version: 1.0
 */
@Getter
@DomainValueObject
public class AuthTaskId {
    public AuthTaskId(Long id) {
        this.id = id;
    }
    private final Long id;

}