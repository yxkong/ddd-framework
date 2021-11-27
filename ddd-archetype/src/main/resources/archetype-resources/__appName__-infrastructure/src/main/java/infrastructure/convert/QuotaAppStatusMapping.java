#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.convert;

import ${package}.domain.model.quota.QuotaAppStatus;
import lombok.Getter;

/**
 * @author ${author}
 * @date 2021-06-07 18:38
 */
@Getter
public enum QuotaAppStatusMapping {

    INIT(QuotaAppStatus.INIT, 0),
    WAITCHECK(QuotaAppStatus.WAITCHECK, 1),
    MANUAL(QuotaAppStatus.MANUAL, 3),
    BACK(QuotaAppStatus.BACK, 4),
    PASS(QuotaAppStatus.PASS, 5),
    REFUSE(QuotaAppStatus.REFUSE, 6);

    private final QuotaAppStatus quotaAppStatus;
    private final Integer codeValue;

    QuotaAppStatusMapping(QuotaAppStatus quotaAppStatus, Integer codeValue) {
        this.quotaAppStatus = quotaAppStatus;
        this.codeValue = codeValue;
    }

    public static QuotaAppStatusMapping mapping(QuotaAppStatus quotaAppStatus) {
        for (QuotaAppStatusMapping value : QuotaAppStatusMapping.values()) {
            if (quotaAppStatus.equals(value.getQuotaAppStatus())) {
                return value;
            }
        }
        return null;
    }

    public static QuotaAppStatus reverseMapping(Integer codeValue) {
        for (QuotaAppStatusMapping value : QuotaAppStatusMapping.values()) {
            if (codeValue.equals(value.getCodeValue())) {
                return value.getQuotaAppStatus();
            }
        }
        return null;
    }
}
