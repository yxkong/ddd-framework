package com.yxkong.demo.domain.service;

import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.dto.context.SmsContext;
import com.yxkong.demo.domain.gateway.SmsGateway;
import lombok.Builder;

/**
 * 短信领域服务
 * @Author: yxkong
 * @Date: 2021/11/15 3:31 PM
 * @version: 1.0
 */
@Builder
@DomainService
public class SmsService {

    SmsGateway smsGateway;

    public ResultBean registerSend(SmsContext smsContext){
        /**
         * 同一手机号，1分钟只发1条
         * 同一手机号，3分钟内验证码相同
         * 同一手机号，一天内发送10条，立即锁定，等待明天再来
         *
         */
        return null;
    }

    public Boolean verifyCodeCheck(SmsContext context){
        /**
         * 是否有效
         * 3次验证还未成功，验证码立即失效
         */
        return false;
    }
}
