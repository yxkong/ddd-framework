#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.convert;

import ${groupId}.common.entity.common.LoginToken;
import ${groupId}.common.entity.event.MsgContent;

import java.util.Objects;

/**
 * 事件转化器
 * @Author: ${author}
 * @Date: 2021/11/26 5:44 PM
 * @version: ${version}
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
     * @param msg
     * @return
     */
    public static MsgContent convert(Long uuid,String mobile,String proId,Integer tenantId,String msgType,String msgNode,Integer status,Object data,String msg  ){
        return  new MsgContent.Builder().uuid(uuid)
                .mobile(mobile)
                .msgType(msgType)
                .msgNode(msgNode)
                .proId(proId)
                .serviceName("ddd-${appName}")
                .sendTime(System.currentTimeMillis())
                .tenantId(tenantId)
                .data(data)
                .status(status)
                .errorMsg(msg)
                .build();

    }
    public static MsgContent convert(String mobile,String proId,Integer tenantId,String msgType,String msgNode,Integer status,LoginToken token,String msg ){
        Long uuid = null;
        if (Objects.nonNull(token)){
            uuid = token.getUuid();
        }
        return  EventConvert.convert(uuid,mobile,proId,tenantId,msgType,msgNode,status,token,msg);

    }
}
