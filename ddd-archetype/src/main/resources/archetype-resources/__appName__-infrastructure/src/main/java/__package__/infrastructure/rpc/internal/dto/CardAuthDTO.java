#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.dto;

import lombok.Data;

/**
 * @Author: ${author}
 * @Date: 2021/6/11 4:40 下午
 * @version: ${version}
 */
@Data
public class CardAuthDTO {

    private Integer status;
    private String failReason;
}