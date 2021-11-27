#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.service;

import ${groupId}.common.annotation.DomainService;
import ${groupId}.common.entity.dto.ResultBean;
import ${groupId}.common.util.ResultBeanUtil;
import ${package}.domain.dto.context.SmsContext;
import ${package}.domain.gateway.SmsGateway;
import ${package}.domain.model.sms.SmsLogId;
import ${package}.domain.model.user.UserObject;
import javafx.util.Pair;
import lombok.Builder;

/**
 * 短信领域服务
 * @Author: ${author}
 * @Date: 2021/11/15 3:31 PM
 * @version: ${version}
 */
@Builder
@DomainService
public class SmsService {

    SmsGateway smsGateway;

    public ResultBean registerSend(SmsContext context){
        /**
         * 同一手机号，1分钟只发1条
         * 同一手机号，3分钟内验证码相同
         * 同一手机号，10分钟内只能发3条
         * 同一手机号，一天内发送10条，立即锁定，等待明天再来
         * 同一ip，一天最多只能发送20条，反欺诈
         * 用redis的zset实现
         */
        Pair<Boolean,String> pair = smsGateway.beforeValidate(context.getUser(),context.getRequestIp());
        if (!pair.getKey()){
            return ResultBeanUtil.fail(pair.getValue(),"");
        }
        //短信记录入库
        SmsLogId smsLogId = smsGateway.saveLog(context);
        if (smsLogId!=null && smsLogId.getId()!=null){
            return ResultBeanUtil.success();
        }
        return ResultBeanUtil.fail("验证码入库失败！",null);

    }

    public ResultBean verifyCodeCheck(UserObject user,String verifyCode,Integer smsType){
        /**
         * 是否有效
         * 3次验证还未成功，验证码立即失效
         */
        Pair<Boolean,String> pair = smsGateway.verifyCodeCheck(user,verifyCode,smsType);
        if (!pair.getKey()){
            return ResultBeanUtil.fail(pair.getValue(),null);
        }
        return ResultBeanUtil.success();
    }
}
