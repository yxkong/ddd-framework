package com.yxkong.demo.domain.model.user;

import com.yxkong.common.annotation.AggregateRoot;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 账户领域对象
 *
 * @Author: yxkong
 * @Date: 2021/5/31 6:13 下午
 * @version: 1.0
 */
@Getter
@Builder
@AggregateRoot
@NoArgsConstructor
public class AccountEntity {
    private AccountId accountId;

    /**
     * 用户对象
     */
    private UserObject user;
    /**
     * 用户状态
     */
    private AccountStatusEnum accountStatus;
    /**
     * 用户注册渠道
     */
    private String proId;

}
