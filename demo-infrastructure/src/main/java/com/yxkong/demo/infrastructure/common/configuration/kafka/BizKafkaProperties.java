package com.yxkong.demo.infrastructure.common.configuration.kafka;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: yxkong
 * @Date: 2021/6/15 2:36 下午
 * @version: 1.0
 */
@Component("bizKafkaProperties")
@ConfigurationProperties(prefix = "spring.kafka")
public class BizKafkaProperties {
    private String enabled;
    private List<String> bizBootstrapServers = new ArrayList<>(Collections.singletonList("localhost:9092"));;
    @Resource
    private KafkaProperties kafkaProperties;

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }


    public List<String> getBizBootstrapServers() {
        return bizBootstrapServers;
    }

    public void setBizBootstrapServers(List<String> bizBootstrapServers) {
        this.bizBootstrapServers = bizBootstrapServers;
    }

    public KafkaProperties getKafkaProperties() {
        return kafkaProperties;
    }
}