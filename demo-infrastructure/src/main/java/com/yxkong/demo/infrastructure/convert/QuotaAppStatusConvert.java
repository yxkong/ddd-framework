package com.yxkong.demo.infrastructure.convert;

import com.yxkong.demo.domain.model.quota.QuotaAppStatus;
import lombok.Getter;

/**
 * @author navyzhung
 * @date 2021-06-07 18:38
 */
@Getter
public enum QuotaAppStatusConvert {

    INIT(QuotaAppStatus.INIT, 0),
    MANUAL(QuotaAppStatus.MANUAL, 3),
    BACK(QuotaAppStatus.BACK, 4),
    PASS(QuotaAppStatus.PASS, 5),
    REFUSE(QuotaAppStatus.REFUSE, 6);

    private final QuotaAppStatus quotaAppStatus;
    private final Integer codeValue;

    QuotaAppStatusConvert(QuotaAppStatus quotaAppStatus, Integer codeValue) {
        this.quotaAppStatus = quotaAppStatus;
        this.codeValue = codeValue;
    }

    public static QuotaAppStatusConvert mapping(QuotaAppStatus quotaAppStatus) {
        for (QuotaAppStatusConvert value : QuotaAppStatusConvert.values()) {
            if (quotaAppStatus.equals(value.getQuotaAppStatus())) {
                return value;
            }
        }
        return null;
    }

    public static QuotaAppStatus reverseMapping(Integer codeValue) {
        for (QuotaAppStatusConvert value : QuotaAppStatusConvert.values()) {
            if (codeValue.equals(value.getCodeValue())) {
                return value.getQuotaAppStatus();
            }
        }
        return null;
    }
}
