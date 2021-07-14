package com.yxkong.demo.infrastructure.persistence.entity.loanandx;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @version 1.0
 * @类介绍 授信决策流申请表
 * @time 2021年06月04日 17:40:19
 **/
@Getter
@Setter
public class RiskApplyDO {
    /**
     *
     */
    private Long id;
    /**
     * 业务系统流水号flowId
     */
    private String flowId;
    /**
     * 业务系统用户号(对应客户表的customerid)
     */
    private Long customerId;

    /**
     * 决策流是否启动成功(0初始化，1成功)
     */
    private Integer applySendStatus;
    /**
     * 风控接口返回的状态(0初始化,1成功)
     */
    private Integer applyResponseStatus;
    /**
     * 风控回调状态(0初始化,9 成功 3失败)
     */
    private Integer applyCallbackStatus;
    /**
     * 风控回流时间
     */
    private Date applyCallbackTime;

    /**
     * 风控回流时间
     */
    private Date noticeQuotaTime;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 租户id
     */
    private Integer tenantId;
    /**
     * 备注
     */
    private String remark;

    /**
     * 唯一id
     */
    private Long applyId;

}