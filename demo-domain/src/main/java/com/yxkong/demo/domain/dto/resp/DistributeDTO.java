package com.yxkong.demo.domain.dto.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

/**
 * 分发返回数据
 * @Author: yxkong
 * @Date: 2021/6/3 6:06 下午
 * @version: 1.0
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
