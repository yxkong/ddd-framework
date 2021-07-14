package com.yxkong.demo.infrastructure.remote.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "单场景额度返参基类")
@Data
public class BaseQuotaSingleDto {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "额度id")
    private Long id;
    /**
     * 用户id 取余分表
     */
    @ApiModelProperty(value = "用户id")
    private Long customerId;
    /**
     * 总额度
     */
    @ApiModelProperty(value = "总额度")
    private BigDecimal totalAmt;
    
    /**
     * 已用总额度
     */
    @ApiModelProperty(value = "已用总额度")
    private BigDecimal totalAmtUsed;
    /**
     * 激活状态，0,初始化，1激活
     */
    @ApiModelProperty(value = "激活状态，0,初始化，1激活")
    private Integer activeStatus;
    /**
     * 审核状态，0初始化（审核中），5激活，6拒绝
     */
    @ApiModelProperty(value = "审核状态，0初始化（审核中），5激活，6拒绝")
    private Integer appStatus;
    /**
     * 激活时间
     */
    @ApiModelProperty(value = "激活时间")
    private Date activeTime;
    /**
     * 冻结状态 0未冻结，1已冻结
     */
    @ApiModelProperty(value = "冻结状态 0未冻结，1已冻结")
    private Integer freezeStatus;
    /**
     * 冻结时间
     */
    @ApiModelProperty(value = "冻结时间")
    private Date freezeTime;
    /**
     * 冻结额度
     */
    @ApiModelProperty(value = "冻结额度")
    private BigDecimal freezeAmt;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 可用额度(总可用)
     */
    @ApiModelProperty(value = "可用额度(总可用)")
    private BigDecimal enableAmt;

}