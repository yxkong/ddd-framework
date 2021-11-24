package com.yxkong.demo.domain.model.sms;

import com.yxkong.common.annotation.DomainEntity;
import com.yxkong.common.constant.TenantEnum;
import lombok.Getter;

/**
 * 日志实体
 * @Author: yxkong
 * @Date: 2021/11/24 10:54 AM
 * @version: 1.0
 */
@DomainEntity
@Getter
public class SmsLogId {
    private Long id;
    private TenantEnum tenant;
    public SmsLogId(Long id,TenantEnum tenant) {
        this.id = id;
        this.tenant = tenant;
    }
}
