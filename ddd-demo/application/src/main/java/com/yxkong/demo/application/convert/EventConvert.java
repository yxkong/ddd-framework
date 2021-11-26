package com.yxkong.demo.application.convert;

import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.event.MsgContent;

import java.util.Date;
import java.util.Objects;

/**
 * 事件转化器
 * @Author: yxkong
 * @Date: 2021/11/26 5:44 PM
 * @version: 1.0
 */
public class EventConvert {

    /**
     * 事件转化器
     * @param uuid  uuid
     * @param mobile 手机号
     * @param proId  渠道
     * @param tenantId 租户
     * @param msgType 消息类型 account,sms
     * @param msgNode 消息节点：register，login
     * @param status 1 成功，0失败
     * @param data
     * @return
     */
    public static MsgContent convert(Long uuid,String mobile,String proId,Integer tenantId,String msgType,String msgNode,Integer status,Object data ){
        return  new MsgContent.Builder().uuid(uuid)
                .mobile(mobile)
                .msgType(msgType)
                .msgNode(msgNode)
                .proId(proId)
                .serviceName("ddd-demo")
                .sendTime(System.currentTimeMillis())
                .tenantId(tenantId)
                .data(data)
                .status(status)
                .build();

    }
    public static MsgContent convert(String mobile,String proId,Integer tenantId,String msgType,String msgNode,Integer status,LoginToken token ){
        Long uuid = null;
        if (Objects.nonNull(token)){
            uuid = token.getUuid();
        }
        return  new MsgContent.Builder().uuid(uuid)
                .mobile(mobile)
                .msgType(msgType)
                .msgNode(msgNode)
                .proId(proId)
                .serviceName("ddd-demo")
                .sendTime(System.currentTimeMillis())
                .tenantId(tenantId)
                .data(token)
                .status(status)
                .build();

    }
}
