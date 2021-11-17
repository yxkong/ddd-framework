package com.yxkong.demo.application.executor;

import com.yxkong.common.constant.ResultStatusEnum;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.util.ResultBeanUtil;
import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.application.convert.RegisterConvert;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.dto.context.SmsContext;
import com.yxkong.demo.domain.gateway.SmsGateway;
import com.yxkong.demo.domain.service.SmsService;
import com.yxkong.demo.infrastructure.common.util.IdWorker;
import com.yxkong.demo.infrastructure.common.util.MD5Utils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 注册应用层
 *
 * @Author: yxkong
 * @Date: 2021/11/15 7:27 PM
 * @version: 1.0
 */
@Service
public class RegisterExecutor {
    @Resource
    private SmsGateway smsGateway;
    @Resource
    private IdWorker idWorker;


    public ResultBean  register(RegisterAppContext context){
        SmsContext smsContext = new SmsContext(context.getUser(), context.getRequestIp(), context.getVerifyCode());
        SmsService smsService = SmsService.builder().smsGateway(smsGateway).build();
        Boolean check = smsService.verifyCodeCheck(smsContext);
        if (!check){
            return ResultBeanUtil.result(ResultStatusEnum.CHECKSMSEXCEPTION);
        }
        /**
         * 随机生成盐值（一个账户，落库以后盐值不变）
         * 如果，是密码注册，加密密码
         * 生成用uuid
         */
        String salt = RandomStringUtils.random(6);
        String md5Pwd = null;
        if (context.getRegisterType()==1){
            //加密的密码
            md5Pwd = MD5Utils.getSaltMD5(context.getPwd(), salt);
        }
        //生成用户的唯一标识
        long uuid = idWorker.nextId();
        RegisterContext registerContext = RegisterConvert.convert(context, md5Pwd, salt, uuid);

        return null;
    }

}
