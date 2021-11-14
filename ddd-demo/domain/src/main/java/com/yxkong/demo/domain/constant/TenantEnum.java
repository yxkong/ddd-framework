package com.yxkong.demo.domain.constant;

import lombok.Getter;

/**
 * 租户枚举
 *
 * @Author: yxkong
 * @Date: 2021/6/2 6:08 下午
 * @version: 1.0
 */
@Getter
public enum TenantEnum {

    main(1001, "main"),
    other(2010, "other");

    private final Integer tenantId;
    private final String name;

    TenantEnum(Integer tenantId, String name) {
        this.tenantId = tenantId;
        this.name = name;
    }

    public static TenantEnum getDefault() {
        return TenantEnum.main;
    }
}
