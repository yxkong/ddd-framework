#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Map;

@ApiModel(value = "单场景额度返参")
@Data
@EqualsAndHashCode(callSuper = true)
public class QuotaSingleVo extends BaseQuotaSingleVo {

    @ApiModelProperty(value = "每条业务线的可用额度")
    private Map<String, BigDecimal> bizTypesEnableAmt;
    @ApiModelProperty(value = "每条业务线的已用额度")
    private Map<String, BigDecimal> bizTypesUsedAmt;
    @ApiModelProperty(value = "每条业务线的最大可用额度")
    private Map<String, BigDecimal> bizTypesMaxEnableAmt;
}