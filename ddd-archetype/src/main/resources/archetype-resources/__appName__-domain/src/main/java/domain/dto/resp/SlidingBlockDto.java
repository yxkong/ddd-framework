#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.resp;

import lombok.Builder;
import lombok.Getter;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/11/15 11:21 AM
 * @version: ${version}
 */
@Getter
@Builder
public class SlidingBlockDto {
    /**
     * 验证结果，true通过，false 未通过
     */
    private Boolean result;
    /**
     * 失败次数
     */
    private Integer failTimes;
    /**
     * 失败信息
     */
    private String  message;
}
