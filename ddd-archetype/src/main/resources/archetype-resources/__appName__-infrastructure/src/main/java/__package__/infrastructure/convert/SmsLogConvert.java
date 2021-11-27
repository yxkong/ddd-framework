#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.convert;

import com.${author}.${appName}.domain.dto.context.SmsContext;
import ${package}.infrastructure.persistence.entity.${appName}.SmsLogDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * smsLog
 * @Author: ${author}
 * @Date: 2021/11/24 11:34 AM
 * @version: ${version}
 */
public class SmsLogConvert {

    public static SmsLogDO convert(SmsContext context){
        SmsLogDO sms = new SmsLogDO();
        sms.setMobile(context.getUser().getMobile());
        sms.setRequestIp(context.getRequestIp());
        sms.setSmsContent(context.getMessage());
        sms.setSmsType(context.getSmsType());
        sms.setSendStatus(1);
        sms.setTenantId(context.getUser().getTenantEnum().getTenantId());
        sms.setSendTime(new Date());
        sms.setVerifyCode(context.getVerifyCode());
        sms.setProId(context.getProId());
        return sms;
    }
}
