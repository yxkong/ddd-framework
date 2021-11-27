#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.executor;

import ${groupId}.common.constant.ResultStatusEnum;
import ${groupId}.common.entity.dto.ResultBean;
import ${groupId}.common.util.ResultBeanUtil;
import ${package}.application.convert.SlidingBlockConvert;
import ${package}.domain.dto.context.SlidingBlockContext;
import ${package}.domain.dto.context.SmsContext;
import ${package}.domain.gateway.SmsGateway;
import ${package}.domain.gateway.slidingblock.SlidingBlockGateway;
import ${package}.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import ${package}.domain.model.user.UserObject;
import ${package}.domain.service.SlidingBlockService;
import ${package}.domain.service.SmsService;
import ${package}.infrastructure.common.util.CommonUtil;
import ${package}.infrastructure.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 短信应用层
 *
 * @Author: ${author}
 * @Date: 2021/11/15 10:17 AM
 * @version: ${version}
 */
@Service
@Slf4j
public class SmsExecutor {
    @Resource
    SlidingBlockGateway slidingBlockGateway;
    @Resource
    Map<String,SlidingBlockSupplierGateway> slidingBlockSupplierGatewayMap;

    @Resource
    SmsGateway smsGateway;



    public ResultBean sendBySlidingBlock(SlidingBlockContext context){

        RLock lock = CommonUtil.REDISSON_CLIENT.getLock("sendBySlidingBlock:"+ context.getUser().getTenantEnum() +":"+ context.getUser().getMobile());
        if (lock.isLocked()) {
            log.warn("用户:{}重复调用验证码,", context.getUser().getMobile());
            return ResultBeanUtil.result(ResultStatusEnum.REPEAT);
        }
        ResultBean resultBean = ResultBeanUtil.fail();
        try {
            lock.tryLock(3, 50, TimeUnit.SECONDS);
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
            log.debug(message);
            SmsService smsService = SmsService.builder().smsGateway(smsGateway).build();
            SmsContext smsContext = new SmsContext(context.getUser(),context.getRequestIp(),verifyCode,context.getSmsType());
            smsContext.setMessage(message);
            smsContext.setProId(context.getProId());
            resultBean = smsService.registerSend(smsContext);
            //以下异步操作
            if (resultBean.isSucc()){
                slidingBlockGateway.updateChecked(context);
            } else {
                slidingBlockGateway.updateFailCount(context);
            }
        }catch (Exception e){
            log.error("用户:{} 发送验证码异常",context.getUser().getMobile(),e);
        }finally {
            lock.unlock();
        }
        return resultBean;
    }
}
