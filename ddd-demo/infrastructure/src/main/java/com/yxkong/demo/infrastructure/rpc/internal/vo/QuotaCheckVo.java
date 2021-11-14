package com.yxkong.demo.infrastructure.rpc.internal.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author navyzhung
 * @date 2021/6/16-18:28
 */
@Data
public class QuotaCheckVo {
    /**
     * 用户id
     */
    private Long customerId;
    private String name;
    private Integer source;
    /**
     * 总额度
     */
    private BigDecimal loanAmtPrimitive;
    /**
     * 额度流转状态,1待自动审核，2 ai，3进入人工信审，4信审退回，5信审通过，6信审拒绝, 7审核异常
     */
    private Integer appStatus;
    /**
     * 冻结标识，0未冻结，1已冻结
     */
    private Integer freezeStatus;
    /**
     * 彩虹评级
     */
    private String gradeLevel;
    /**
     * 1:自动审核直接通过  2：人工审核通过,（只要人审过，就是2，以后不更新）
     */
    private Integer accessWay;

    private Integer channelId;

    private Integer isOperatorCheck;

    private Integer isAccess;
}
