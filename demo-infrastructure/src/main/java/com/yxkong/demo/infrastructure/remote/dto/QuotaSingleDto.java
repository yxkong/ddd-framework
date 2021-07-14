package com.yxkong.demo.infrastructure.remote.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

@ApiModel(value = "单场景额度返参")
@Data
@EqualsAndHashCode(callSuper = true)
public class QuotaSingleDto extends BaseQuotaSingleDto {

    @ApiModelProperty(value = "每条业务线的可用额度")
    private Map<String, BigDecimal> bizTypesEnableAmt;
    @ApiModelProperty(value = "每条业务线的已用额度")
    private Map<String, BigDecimal> bizTypesUsedAmt;
    @ApiModelProperty(value = "每条业务线的最大可用额度")
    private Map<String, BigDecimal> bizTypesMaxEnableAmt;
}