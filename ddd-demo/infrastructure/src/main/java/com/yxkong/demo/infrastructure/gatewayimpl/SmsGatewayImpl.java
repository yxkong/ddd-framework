package com.yxkong.demo.infrastructure.gatewayimpl;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.dto.context.SmsContext;
import com.yxkong.demo.domain.gateway.SmsGateway;
import com.yxkong.demo.domain.model.sms.SmsLogId;
import com.yxkong.demo.domain.model.user.UserObject;
import com.yxkong.demo.infrastructure.common.util.CacheUtils;
import com.yxkong.demo.infrastructure.convert.SmsLogConvert;
import com.yxkong.demo.infrastructure.persistence.entity.demo.SmsLogDO;
import com.yxkong.demo.infrastructure.persistence.mapper.demoandx.SmsLogMapper;
import javafx.util.Pair;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Iterator;
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
@Slf4j
public class SmsGatewayImpl implements SmsGateway {
    @Resource
    private SmsLogMapper smsLogMapper;
    public static final  String PREFIX = "userApi:registerSms:";
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;
    @Override
    public ResultBean send(UserObject user, String requestIp) {
        return null;
    }

    @Override
    public SmsLogId saveLog(SmsContext context) {
        SmsLogDO smsLogDO = SmsLogConvert.convert(context);
        smsLogMapper.insertSelective(smsLogDO);
        return new SmsLogId(smsLogDO.getId(), context.getUser().getTenantEnum());
    }

    @Override
    public Pair<Boolean,String> validate(UserObject user, String requestIp) {
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
        Set set = redisTemplate.opsForZSet().rangeByScore(key, getScore(preMinute), l);
        if (set.size()> 10){
            log.info("锁定用户24小时");
            return new Pair<>(false,"同一手机号，一天内发送10条，立即锁定，等待明天再来");
        }
        if (count(set,1,1)){
            return new Pair<>(false,"同一手机号，1分钟只发1条");
        }
        if (count(set,3,10)){
            return new Pair<>(false,"同一手机号，10分钟内只能发3条");
        }

        //内存内判断
        redisTemplate.opsForZSet().add(key,l+"",l);
        redisTemplate.expire(key,preMinute, TimeUnit.MINUTES);
        String ipKey = CacheUtils.generatorKey("userApi","registerSms",requestIp);
        Long count = redisTemplate.opsForZSet().count(ipKey, getScore(preMinute), l);
        if (count.longValue()>20L){
            return new Pair<>(false,"同一ip，一天最多只能发送20条，反欺诈");
        }
        redisTemplate.opsForZSet().add(ipKey,l+"",l);
        redisTemplate.expire(ipKey,preMinute, TimeUnit.MINUTES);
        return new Pair<>(true,"校验通过！");
    }
    private boolean count(Set set,int count,int minute){
        double score = getScore(minute);
//        long num = 0;
        long num = set.stream().filter(s-> Long.parseLong(s.toString())>score).count();
//
//        Iterator it = set.iterator();
//        while (it.hasNext() && num<=count){
//            Object next = it.next();
//            if (Long.parseLong(next.toString())>score){
//                num++;
//            }
//        }
        if (num>=count){
            return true;
        }
        return false;
    }
    private long getScore(int minute){
        return System.currentTimeMillis()-minute*60*1000;
    }
}
