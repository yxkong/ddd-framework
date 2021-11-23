package com.yxkong.demo.infrastructure.gatewayimpl;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.gateway.SmsGateway;
import com.yxkong.demo.domain.model.user.UserObject;
import com.yxkong.demo.infrastructure.common.util.CacheUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 短信网关
 *
 * @Author: yxkong
 * @Date: 2021/11/17 8:10 PM
 * @version: 1.0
 */
@Service
public class SmsGatewayImpl implements SmsGateway {
    public static final  String PREFIX = "userApi:registerSms:";
    @Resource(name="stringRedisTemplate")
    private RedisTemplate template;
    @Override
    public ResultBean send(UserObject user, String requestIp) {
        return null;
    }

    @Override
    public Boolean validate(UserObject user, String requestIp) {
        /**
         * 同一手机号，1分钟只发1条
         * 同一手机号，3分钟内验证码相同
         * 同一手机号，10分钟内只能发3条
         * 同一手机号，一天内发送10条，立即锁定，等待明天再来
         * 同一ip，一天最多只能发送20条，反欺诈
         * 用redis的zset实现
         */
        String key = CacheUtils.generatorKey("userApi","registerSms",user.getTenantEnum().getTenantId()+"",user.getMobile());
        long l = System.currentTimeMillis();
        int preMinute = 60*24;
        Set set = template.opsForZSet().rangeByScore(key, getScore(preMinute), l);
        if (set.size()> 10){
            return false;
        }
        template.opsForZSet().add(key,null,l);
        template.expire(key,preMinute, TimeUnit.MINUTES);
        String ipKey = CacheUtils.generatorKey("userApi","registerSms",requestIp);
        Long count = template.opsForZSet().count(ipKey, getScore(preMinute), l);
        if (count.longValue()>20L){
            return false;
        }
        template.opsForZSet().add(ipKey,null,l);
        template.expire(ipKey,preMinute, TimeUnit.MINUTES);
        return true;
    }
    private double getScore(int minute){
        return System.currentTimeMillis()-minute*60*1000;
    }
}
