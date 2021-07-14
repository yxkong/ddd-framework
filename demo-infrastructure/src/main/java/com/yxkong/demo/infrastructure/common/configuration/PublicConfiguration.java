package com.yxkong.demo.infrastructure.common.configuration;

import com.yxkong.demo.infrastructure.common.util.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yxkong
 * @date 2021-06-07 16:19
 */
@Configuration
public class PublicConfiguration {

    /**
     * 万卡统一的分布式id生成算法
     *
     * @param idWorkerProperties
     * @return
     * @throws Exception
     */
    @Bean
    public IdWorker idWorker(IdWorkerProperties idWorkerProperties) throws Exception {
        List<String> cluster = idWorkerProperties.getCluster();
        int skip = idWorkerProperties.getSkip();
        if (cluster == null) {
            cluster = new ArrayList<>();
        }
        return new IdWorker(cluster, skip);
    }

}
