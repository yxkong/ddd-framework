package com.yxkong.demo.application.executor;

import com.yxkong.common.constant.ResultStatusEnum;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.exception.DomainException;
import com.yxkong.common.util.ResultBeanUtil;
import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.application.convert.RegisterConvert;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.dto.context.SmsContext;
import com.yxkong.demo.domain.gateway.RegisterGateway;
import com.yxkong.demo.domain.gateway.SmsGateway;
import com.yxkong.demo.domain.service.AccountService;
import com.yxkong.demo.domain.service.SmsService;
import com.yxkong.demo.infrastructure.common.util.CommonUtil;
import com.yxkong.demo.infrastructure.common.util.IdWorker;
import com.yxkong.demo.infrastructure.common.util.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 注册应用层
 *
 * @Author: yxkong
 * @Date: 2021/11/15 7:27 PM
 * @version: 1.0
 */
@Service
@Slf4j
public class AccountExecutor {
    @Resource
    private RegisterGateway registerGateway;
    @Resource
    private SmsGateway smsGateway;
    @Resource
    private IdWorker idWorker;


    public ResultBean  register(RegisterAppContext context){
        RLock lock = CommonUtil.REDISSON_CLIENT.getLock("register:"+ context.getUser().getTenantEnum() +":"+ context.getUser().getMobile());
        if (lock.isLocked()) {
            log.warn("用户:{}注册中，请不要重复调用,", context.getUser().getMobile());
            return ResultBeanUtil.result(ResultStatusEnum.REPEAT);
        }
        ResultBean resultBean = ResultBeanUtil.fail();
        try {
            lock.tryLock(5, 10, TimeUnit.SECONDS);
            SmsContext smsContext = new SmsContext(context.getUser(), context.getRequestIp(), context.getVerifyCode());
            SmsService smsService = SmsService.builder().smsGateway(smsGateway).build();
            resultBean = smsService.verifyCodeCheck(smsContext.getUser(),smsContext.getVerifyCode(),1);
            if (!resultBean.isSucc()){
                return resultBean;
            }
            /**
             * 随机生成盐值（一个账户，落库以后盐值不变）
             * 如果，是密码注册，加密密码
             * 生成用uuid
             */
            String salt = RandomStringUtils.randomAlphabetic(6);
            String md5Pwd = null;
            if (context.getRegisterType()==1){
                //加密的密码
                md5Pwd = MD5Utils.getSaltMD5(context.getPwd(), salt);
            }
            //生成用户的唯一标识，后续用户的关联用uuid，不能用id，脑裂后无法对上数据
            long uuid = idWorker.nextId();
            RegisterContext registerContext = RegisterConvert.convert(context, md5Pwd, salt, uuid);
            AccountService accountService = AccountService.builder().registerGateway(registerGateway).build();
            resultBean = accountService.register(registerContext);
        } catch (DomainException e) {
            log.error("用户{}注册领域层失败，异常信息：",context.getUser().getMobile(),e);
            return ResultBeanUtil.result(e);
        } catch (Exception e){
            log.error("用户{}注册应用层失败，异常信息：",context.getUser().getMobile(),e);
            resultBean = ResultBeanUtil.fail("系统异常，请稍后再试！",null);
        }finally {
            smsGateway.useStatus(context.getUser(),context.getVerifyCode(),1);
        }
        return resultBean;
    }

}
