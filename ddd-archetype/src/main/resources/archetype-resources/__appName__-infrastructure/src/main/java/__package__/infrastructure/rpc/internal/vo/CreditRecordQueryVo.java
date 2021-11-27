#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import lombok.Data;

@Data
public class CreditRecordQueryVo {

    private String certId;
    private String mobile;
    private Long customerId;
    private String userName;
    // 模式
    private String model = "fr";
    private String orderNo;
    private String node = "auth";
    private String proId;
}
