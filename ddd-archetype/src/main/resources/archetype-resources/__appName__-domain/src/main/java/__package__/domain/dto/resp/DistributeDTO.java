#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 分发返回数据
 * @Author: ${author}
 * @Date: 2021/6/3 6:06 下午
 * @version: ${version}
 */
@Data
@Builder
@ApiModel("分发返回数据体")
public class DistributeDTO {
    /**
     * 流程ID
     */
    @ApiModelProperty("流程ID")
    private String authUuid;
    /**
     * 分发路径
     */
    @ApiModelProperty("分发路径")
    private DistributePath distributePath;
    
    /**
     * 决策流id（只有进入等待页已发起才会有）
     */
    private Long taskId;
}
