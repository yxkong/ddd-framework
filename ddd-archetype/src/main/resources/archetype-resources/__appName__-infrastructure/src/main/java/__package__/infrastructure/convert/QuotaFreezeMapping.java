#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.convert;

import ${package}.domain.model.quota.QuotaFreezeStatus;
import lombok.Getter;

/**
 * @Author: ${author}
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
