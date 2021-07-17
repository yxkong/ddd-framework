package com.yxkong.demo.infrastructure.convert;

import com.yxkong.demo.domain.model.quota.QuotaEntity;
import com.yxkong.demo.domain.model.user.CustomerId;
import com.yxkong.demo.infrastructure.remote.dto.QuotaSingleDto;
import org.springframework.beans.BeanUtils;

/**
 * @Author: yxkong
 * @Date: 2021/7/17 12:14 下午
 * @version: 1.0
 */
public class QuotaConvert {

    public static QuotaEntity convert(QuotaSingleDto dto, CustomerId customerId){
        QuotaEntity quotaEntity = QuotaEntity.builder().customerId(customerId)
                .quotaAppStatus(QuotaAppStatusConvert.reverseMapping(dto.getAppStatus()))
                .quotaFreezeStatus(QuotaFreezeConvert.reverseMapping(dto.getFreezeStatus())).build();
        BeanUtils.copyProperties(dto, quotaEntity);
        return quotaEntity;
    }
}