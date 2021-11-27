#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import lombok.Data;

/**
 * @author 
 * @date 2021/6/15 19:40
 */
@Data
public class HasFaceResponseVo {

    private Boolean hasFace;
    private String code_msg;
    private Integer code;
}
