package com.yxkong.demo.infrastructure.common.configuration.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.Assert;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yxkong
 * @Date: 2021/6/10 11:09 上午
 * @version: 1.0
 */
@EnableCaching
@Configuration
@AutoConfigureAfter({CacheAutoConfiguration.class})
@ConditionalOnClass(RedisOperations.class)
@EnableConfigurationProperties({RedisProperties.class, CacheProperties.class, RedisCacheExpiresProperties.class})
public class CustomRedisCacheManagerConfiguration {
    private final CacheProperties cacheProperties;

    public CustomRedisCacheManagerConfiguration(CacheProperties cacheProperties) {
        this.cacheProperties = cacheProperties;
    }

    @Bean
    @ConditionalOnMissingBean(name = "redisCacheManager")
    public RedisCacheManager redisCacheManager(
            @Qualifier("redissonConnectionFactory") RedisConnectionFactory redisConnectionFactory,
            RedisCacheExpiresProperties redisCacheExpiresProperties) {

        RedisCacheManager.RedisCacheManagerBuilder builder =
                RedisCacheManager.builder(redisConnectionFactory).cacheDefaults(determineConfiguration());

        // 设置自定义redis cacheName以及对应的失效时间
        Map<String, Long> cacheConfigurations = redisCacheExpiresProperties.getCacheExpires();
        if (cacheConfigurations != null && cacheConfigurations.size() > 0) {
            Map<String, RedisCacheConfiguration> redisCacheConfigurations = new HashMap<>();
            for (String cacheName : cacheConfigurations.keySet()) {
                Assert.notNull(cacheName, "CacheName must not be null!");

                long ttl = cacheConfigurations.get(cacheName);
                Assert.isTrue(ttl > 0, "Expire must not be null!");

                RedisCacheConfiguration redisCacheConfiguration = determineConfiguration2(cacheName, ttl);
                redisCacheConfigurations.put(cacheName, redisCacheConfiguration);

            }
            builder.withInitialCacheConfigurations(redisCacheConfigurations);
        }

        return builder.build();
    }

    private RedisCacheConfiguration determineConfiguration() {

        CacheProperties.Redis redisProperties = this.cacheProperties.getRedis();
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));

        if (redisProperties.getTimeToLive() != null) {
            config = config.entryTtl(redisProperties.getTimeToLive());
        }
        if (redisProperties.getKeyPrefix() != null) {
            config = config.prefixCacheNameWith(redisProperties.getKeyPrefix());
        }
        if (!redisProperties.isCacheNullValues()) {
            config = config.disableCachingNullValues();
        }
        if (!redisProperties.isUseKeyPrefix()) {
            config = config.disableKeyPrefix();
        }

        return config;
    }

    private RedisCacheConfiguration determineConfiguration2(String cacheName,long ttl) {

        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig();

        config = config.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()));
        config = config.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

        config = config.entryTtl(Duration.ofSeconds(ttl));
        config = config.disableCachingNullValues();

        return config;
    }
}