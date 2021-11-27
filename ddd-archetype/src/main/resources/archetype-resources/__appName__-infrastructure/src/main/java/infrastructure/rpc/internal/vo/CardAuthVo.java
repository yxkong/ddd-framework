#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardAuthVo {

    private String elementType;

    private String bankCode;

    private String certNo;

    private String bankCardNo;

    private String cardPhoneNo;

    private String termOfValidity;

    private String cvvCode;

    private String channelNo;

    private String realName;

}
