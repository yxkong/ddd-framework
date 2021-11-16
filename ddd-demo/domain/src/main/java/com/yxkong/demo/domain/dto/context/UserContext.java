package com.yxkong.demo.domain.dto.context;

import com.yxkong.common.constant.TenantEnum;
import com.yxkong.demo.domain.model.user.AccountId;
import com.yxkong.demo.domain.model.user.CustomerId;
import lombok.Getter;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/15 6:42 PM
 * @version: 1.0
 */
@Getter
public class UserContext {
    private AccountId accountId;
    private CustomerId customerId;

    public UserContext(AccountId accountId, CustomerId customerId) {
        this.accountId = accountId;
        this.customerId = customerId;
    }

    public UserContext(Long accountId, Long customerId, TenantEnum tenant) {
        this.accountId = new AccountId(accountId,null,tenant);
        this.customerId = new CustomerId(customerId,tenant);
    }
}
