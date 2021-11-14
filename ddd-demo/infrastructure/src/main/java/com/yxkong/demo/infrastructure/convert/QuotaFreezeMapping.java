package com.yxkong.demo.infrastructure.convert;

import com.yxkong.demo.domain.model.quota.QuotaFreezeStatus;
import lombok.Getter;

/**
 * @author navyzhung
 * @date 2021-06-07 18:38
 */
@Getter
public enum QuotaFreezeMapping {

    NORMAL(QuotaFreezeStatus.NORMAL, 0), FREEZE(QuotaFreezeStatus.FREEZE, 1);

    private final QuotaFreezeStatus quotaFreezeStatus;
    private final Integer codeValue;

    QuotaFreezeMapping(QuotaFreezeStatus quotaFreezeStatus, Integer codeValue) {
        this.quotaFreezeStatus = quotaFreezeStatus;
        this.codeValue = codeValue;
    }

    public static QuotaFreezeStatus reverseMapping(Integer codeValue) {
        for (QuotaFreezeMapping value : QuotaFreezeMapping.values()) {
            if (codeValue.equals(value.getCodeValue())) {
                return value.getQuotaFreezeStatus();
            }
        }
        return null;
    }
}
