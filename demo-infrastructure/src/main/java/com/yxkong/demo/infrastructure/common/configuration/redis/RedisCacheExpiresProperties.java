package com.yxkong.demo.infrastructure.common.configuration.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

/**
 * @Author: yxkong
 * @Date: 2021/6/10 11:08 上午
 * @version: 1.0
 */
@ConfigurationProperties(prefix = "spring.cache.redis")
public class RedisCacheExpiresProperties {
    private Map<String, Long> cacheExpires;

    public Map<String, Long> getCacheExpires() {
        return cacheExpires;
    }

    public void setCacheExpires(Map<String, Long> cacheExpires) {
        this.cacheExpires = cacheExpires;
    }
}