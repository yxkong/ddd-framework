package com.yxkong.demo.infrastructure.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author yxkong
 * @date 2020-08-03 14:44
 */
@Component
public class CommonUtil {

    public static ObjectMapper OBJECT_MAPPER;

    public static IdWorker ID_WORKER;

    public static RedissonClient REDISSON_CLIENT;


    @Autowired
    public void setJacksonObjectMapper(ObjectMapper objectMapper) {
        CommonUtil.OBJECT_MAPPER = objectMapper;
    }

    @Autowired
    public void setIdWorker(IdWorker idWorker) {
        CommonUtil.ID_WORKER = idWorker;
    }

    @Autowired
    public void setRedissonClient(RedissonClient redissonClient) {
        CommonUtil.REDISSON_CLIENT = redissonClient;
    }

}
