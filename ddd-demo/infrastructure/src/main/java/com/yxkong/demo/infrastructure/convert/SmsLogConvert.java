package com.yxkong.demo.infrastructure.convert;

import com.yxkong.demo.domain.dto.context.SmsContext;
import com.yxkong.demo.infrastructure.persistence.entity.demo.SmsLogDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * smsLog
 * @Author: yxkong
 * @Date: 2021/11/24 11:34 AM
 * @version: 1.0
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
