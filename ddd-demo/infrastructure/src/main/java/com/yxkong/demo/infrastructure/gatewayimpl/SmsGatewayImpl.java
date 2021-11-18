package com.yxkong.demo.infrastructure.gatewayimpl;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.gateway.SmsGateway;
import com.yxkong.demo.domain.model.user.UserObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

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
    @Resource
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
        String key = PREFIX + user.getMobile();
        Set set = template.opsForZSet().rangeByScore(key, getScore(60*24), System.currentTimeMillis());
        if (set.size()> 10){
            return false;
        }

        return null;
    }
    private double getScore(int minute){
        return System.currentTimeMillis()-minute*60*1000;
    }
}
