package com.yxkong.demo.domain.dto.context;

import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.demo.domain.model.user.CustomerId;
import lombok.Getter;

/**
 * 分发上下文
 *
 * @Author: yxkong
 * @Date: 2021/6/3 6:15 下午
 * @version: 1.0
 */
@Getter
public class DistributeContext extends UserContext {
	private String mobile;
    private CustomerId customerId;
    public DistributeContext(LoginToken loginToken) {
        super(loginToken.getAccountId(), loginToken.getCustomerId(), loginToken.getTenantId());
        this.mobile = loginToken.getMobile();
        this.customerId = new CustomerId(loginToken.getCustomerId(), loginToken.getTenantId());
    }
}