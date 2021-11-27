#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author ${author}
 * @date 2021-06-15 17:37
 */
@ApiModel(value = "提额接口反参")
@Data
@NoArgsConstructor
public class ChangeQuotaVo {

    @JsonIgnore
    private Long id;
    @ApiModelProperty(value = "冻结状态，1：已冻结；0：未冻结")
    private Integer freezeStatus;
    @ApiModelProperty(value = "实际提额额度")
    private BigDecimal amt;
    @ApiModelProperty(value = "真实过期时间")
    private Date expireTime;
    @ApiModelProperty(value = "可用额度")
    private BigDecimal loanAmt;
}
