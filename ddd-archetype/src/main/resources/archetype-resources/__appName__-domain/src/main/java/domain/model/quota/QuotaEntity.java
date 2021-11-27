#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.quota;

import com.yxkong.common.annotation.AggregateRoot;
import ${package}.domain.model.user.CustomerId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 额度领域对象
 *
 * @Author: ${author}
 * @Date: 2021/6/3 4:10 下午
 * @version: ${version}
 */
@Getter
@Setter
@Builder
@AggregateRoot
public class QuotaEntity {

    private CustomerId customerId;

    private QuotaAppStatus quotaAppStatus;

    private QuotaFreezeStatus quotaFreezeStatus;
    /**
     * 激活额度
     */
    private BigDecimal activeAmt;
    /**
     * 可用额度(总可用)
     */
    private BigDecimal enableAmt;
    /**
     * 临时额度总额
     */
    private BigDecimal tempTotalQuota;
    /**
     * 已用总额度
     */
    private BigDecimal totalAmtUsed;

    public boolean isInit() {
        return QuotaAppStatus.INIT.equals(quotaAppStatus);
    }

    public boolean isManual() {
        return QuotaAppStatus.MANUAL.equals(quotaAppStatus);
    }

    public boolean isPass() {
        return QuotaAppStatus.PASS.equals(quotaAppStatus);
    }

    public boolean isRefuse() {
        return QuotaAppStatus.REFUSE.equals(quotaAppStatus);
    }

    public boolean isBack() {
        return QuotaAppStatus.BACK.equals(quotaAppStatus);
    }

    public boolean freezeOrNot() {
        if (QuotaFreezeStatus.FREEZE.equals(quotaFreezeStatus)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    /**
     * 是否首次发起决策流
     * @param quotaCheckEntity 信审对应的状态
     * @return true：表示首次
     */
    public boolean firstOrNot(QuotaEntity quotaCheckEntity) {
        return !((isRefuse() || isPass()) &&  (quotaCheckEntity.isManual() || quotaCheckEntity.isBack()));
    }

    public boolean manualOrBack() {
        return isManual() || isBack();
    }
}
