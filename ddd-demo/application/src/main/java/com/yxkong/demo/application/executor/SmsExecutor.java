package com.yxkong.demo.application.executor;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.application.convert.SlidingBlockConvert;
import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.dto.context.SmsContext;
import com.yxkong.demo.domain.gateway.SmsGateway;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockGateway;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import com.yxkong.demo.domain.model.user.UserObject;
import com.yxkong.demo.domain.service.SlidingBlockService;
import com.yxkong.demo.domain.service.SmsService;
import com.yxkong.demo.infrastructure.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 短信应用层
 *
 * @Author: yxkong
 * @Date: 2021/11/15 10:17 AM
 * @version: 1.0
 */
@Service
public class SmsExecutor {
    @Resource
    SlidingBlockGateway slidingBlockGateway;
    @Resource
    Map<String,SlidingBlockSupplierGateway> slidingBlockSupplierGatewayMap;

    @Resource
    SmsGateway smsGateway;



    public ResultBean sendBySlidingBlock(SlidingBlockContext context){
        SlidingBlockSupplierGateway gateway = SlidingBlockConvert.get(slidingBlockSupplierGatewayMap, context.getSlidingBlock());
        SlidingBlockService slidingBlockService = SlidingBlockService.builder().slidingBlockGateway(slidingBlockGateway).slidingBlockSupplierGateway(gateway).build();
        ResultBean check = slidingBlockService.check(context);
        if (!check.isSucc()){
            return check;
        }
        /**
         * 滑块验证通过发送短信验证码
         *  1，获取验证码模板
         *  2，生成验证码
         *  3，调用发送领域模块
         */

        String verifyCode = StringUtils.randomNumber(6);
        String message = String.format("【5ycode】欢迎您，您的注册验证码为：%s，请在3分钟内使用。", verifyCode);
        SmsService smsService = SmsService.builder().smsGateway(smsGateway).build();
        SmsContext smsContext = new SmsContext(context.getUser(),context.getRequestIp(),verifyCode);
        smsContext.setMessage(message);
        ResultBean resultBean = smsService.registerSend(smsContext);
        //以下异步操作
        if (resultBean.isSucc()){
            slidingBlockGateway.updateChecked(context);
        } else {
            slidingBlockGateway.updateFailCount(context);
        }
        return resultBean;
    }
}
