package com.yxkong.common.entity.event;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 事件包装体
 *
 * @Author: yxkong
 * @Date: 2021/4/5 8:28 下午
 * @version: 1.0
 */
@ApiModel("通用事件包装体")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MsgContent<T> implements Serializable {
    private static final long serialVersionUID = -5115473473963556887L;

    /**
     * 来源的项目名称,使用驼峰，英文
     */
    @ApiModelProperty("来源的项目名称,使用驼峰，英文")
    protected String serviceName;

    /**
     * 消息类型，用于标识事件的唯一性，消息第一级，例如：credit
     */
    private String msgType;
    /**
     * 消息节点
     */
    @ApiModelProperty("消息节点")
    protected String msgNode;
    /**
     * 消息发送的时间戳
     */
    @ApiModelProperty("消息发送的时间戳")
    protected Long sendTime;
    /**
     * 消息唯一标识，如果没有data进行md5
     */
    @ApiModelProperty("消息唯一标识，如果没有data进行md5")
    protected String msgId;

    /**
     * 消息体
     */
    @ApiModelProperty("消息体")
    protected T data;
    /**
     * 消息状态，1成功，0，失败
     */
    @ApiModelProperty("消息状态，1成功，0，失败")
    private Integer status;

    /**
     * 用户操作来源
     */
    private String proId;
    private String source;
    private Long uuid;

    /**
     * 手机号
     */
    private String mobile;

    private Integer tenantId;

    /**
     * 失败信息
     */
    private String errorMsg;

}