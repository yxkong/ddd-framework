#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import lombok.Data;

@Data
public class QuotaSingleQueryVo {

    private Long customerId;

    private Boolean bizFlag = false;

    private Integer quotaType = 0;

    public QuotaSingleQueryVo(Long customerId) {
        this.customerId = customerId;
    }
}
