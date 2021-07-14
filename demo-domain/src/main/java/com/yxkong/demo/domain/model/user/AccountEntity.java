package com.yxkong.demo.domain.model.user;

import com.yxkong.common.annotation.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

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
public class AccountEntity {
    private AccountId accountId;
    /**
     * 用户手机号码
     */
    private String mobile;
    /**
     * 用户中心的uuid
     */
    private String uuid;
    /**
     * 用户中心的客户id
     */
    private String custNo;
    /**
     * 用户注册渠道
     */
    private String proId;
}
