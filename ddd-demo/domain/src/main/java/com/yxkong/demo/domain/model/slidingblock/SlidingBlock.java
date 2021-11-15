package com.yxkong.demo.domain.model.slidingblock;

import com.yxkong.common.annotation.DomainValueObject;
import com.yxkong.demo.domain.constant.TenantEnum;
import lombok.Getter;

/**
 * 滑块值对象（在这里理解为值对象）
 * @Author: yxkong
 * @Date: 2021/11/15 1:50 PM
 * @version: 1.0
 */
@DomainValueObject
@Getter
public class SlidingBlock {
    /**
     * 滑块id
     */
    private String slidingBlockId;
    /**
     * 租户
     */
    private TenantEnum tenantEnum;
    /**
     * 滑块厂商
     */
    private String slidingBlockSupplier;

    public SlidingBlock(TenantEnum tenantEnum, String slidingBlockId, String slidingBlockSupplier) {
        this.tenantEnum = tenantEnum;
        this.slidingBlockId = slidingBlockId;
        this.slidingBlockSupplier = slidingBlockSupplier;
    }
}
