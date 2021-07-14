package com.yxkong.demo.infrastructure.convert;

import com.yxkong.demo.domain.model.quota.QuotaFreezeStatus;
import lombok.Getter;

/**
 * @author yxkong
 * @date 2021-07-14
 */
@Getter
public enum QuotaFreezeConvert {

    NORMAL(QuotaFreezeStatus.NORMAL, 0), FREEZE(QuotaFreezeStatus.FREEZE, 1);

    private final QuotaFreezeStatus quotaFreezeStatus;
    private final Integer codeValue;

    QuotaFreezeConvert(QuotaFreezeStatus quotaFreezeStatus, Integer codeValue) {
        this.quotaFreezeStatus = quotaFreezeStatus;
        this.codeValue = codeValue;
    }

    public static QuotaFreezeStatus reverseMapping(Integer codeValue) {
        for (QuotaFreezeConvert value : QuotaFreezeConvert.values()) {
            if (codeValue.equals(value.getCodeValue())) {
                return value.getQuotaFreezeStatus();
            }
        }
        return null;
    }
}
