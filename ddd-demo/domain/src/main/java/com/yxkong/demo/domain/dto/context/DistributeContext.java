package com.yxkong.demo.domain.dto.context;

import com.yxkong.common.constant.TenantEnum;
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
    public DistributeContext(LoginToken token) {
        super(token.getAccountId(), token.getCustomerId(),TenantEnum.get(token.getTenantId()));
        this.mobile = token.getMobile();
        this.customerId = new CustomerId(token.getCustomerId(),  TenantEnum.get(token.getTenantId()));
    }
}