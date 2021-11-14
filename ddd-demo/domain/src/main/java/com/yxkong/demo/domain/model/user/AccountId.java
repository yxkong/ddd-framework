package com.yxkong.demo.domain.model.user;

import com.yxkong.common.annotation.DomainEntity;
import lombok.Getter;

/**
 * 账户实体
 * ps: 对于我们来说id+ 组合才能唯一确定账户
 * @Author: yxkong
 * @Date: 2021/5/31 4:58 下午
 * @version: 1.0
 */
@DomainEntity
@Getter
public class AccountId {
    private Long uuid;
    private Integer tenantId;
    public AccountId(Long uuid,Integer tenantId) {
        this.uuid = uuid;
        this.tenantId = tenantId;
    }
}