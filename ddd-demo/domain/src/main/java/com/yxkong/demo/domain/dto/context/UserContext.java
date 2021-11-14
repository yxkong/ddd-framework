package com.yxkong.demo.domain.dto.context;

import com.yxkong.demo.domain.model.user.AccountId;
import com.yxkong.demo.domain.model.user.CustomerId;
import lombok.Getter;

/**
 * 用户上下文
 *
 * @Author: yxkong
 * @Date: 2021/6/3 6:58 下午
 * @version: 1.0
 */
@Getter
public class UserContext {
    private AccountId accountId;
    private CustomerId customerId;

    public UserContext(Long accountId, Long customerId, Integer tenantId) {
        this.accountId = new AccountId(accountId, tenantId);
        this.customerId = new CustomerId(customerId, tenantId);
    }
}