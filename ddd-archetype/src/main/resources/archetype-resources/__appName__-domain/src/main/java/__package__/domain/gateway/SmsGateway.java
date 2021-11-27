#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.gateway;

import ${groupId}.common.entity.dto.ResultBean;
import ${package}.domain.dto.context.SmsContext;
import ${package}.domain.model.sms.SmsLogId;
import ${package}.domain.model.user.UserObject;
import javafx.util.Pair;

/**
 * 短信网关
 * @Author: ${author}
 * @Date: 2021/11/15 3:16 PM
 * @version: ${version}
 */
public interface SmsGateway {

    /**
     * 发送短信验证码
     * @param user 用户值对象
     * @param requestIp 请求ip
     * @return
     */
    ResultBean send(UserObject user,String requestIp);


    /**
     * 保存发送记录
     * @param context
     * @return
     */
    SmsLogId saveLog(SmsContext context);

    /**
     * 发送前校验
     * @param user
     * @param requestIp
     * @return
     */
    Pair<Boolean,String> beforeValidate(UserObject user, String requestIp);


    /**
     * 验证验校验
     * @param user
     * @param verifyCode
     * @return
     */
    Pair<Boolean, String> verifyCodeCheck(UserObject user, String verifyCode,Integer smsType);

    /**
     * 更新验证码使用状态
     * @param user
     * @param verifyCode
     */
    void useStatus(UserObject user, String verifyCode,Integer smsType);
}
