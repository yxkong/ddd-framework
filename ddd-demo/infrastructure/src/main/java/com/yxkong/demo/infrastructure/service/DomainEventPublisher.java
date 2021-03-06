package com.yxkong.demo.infrastructure.service;

import com.yxkong.demo.infrastructure.common.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 事件发送
 * @Author: yxkong
 * @Date: 2021/11/26 5:37 PM
 * @version: 1.0
 */
@Slf4j
@Service
public class DomainEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Resource(name = "bizKafkaTemplate")
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public void publish(Object domainEvent) {
        applicationEventPublisher.publishEvent(domainEvent);
    }

    public void batchPublishUseListener(List<Object> domainEvents) {
        for (Object domainEvent : domainEvents) {
            applicationEventPublisher.publishEvent(domainEvent);
        }
    }

    public void publishUseKafka(Object domainEvent, String topic) {
        publishUseKafka(domainEvent, topic, null);
    }

    public void publishUseKafka(Object domainEvent, String topic, String key) {
        String data = JsonUtils.toJson(domainEvent);
        ListenableFuture<SendResult<Object, Object>> listenableFuture = null;
        if (StringUtils.isBlank(key)) {
            listenableFuture = kafkaTemplate.send(topic, data);
        } else {
            listenableFuture = kafkaTemplate.send(topic, key, data);
        }
        listenableFuture.addCallback((t) -> log.info("发送kafka数据成功.topic={},msg={}", topic, data),
                throwable -> log.error("发送kafka数据失败.topic={},msg={}", topic, data, throwable));
    }

    public void batchPublishUseKafka(Map<String, Object> domainEvents) {
        for (Map.Entry<String, Object> entry : domainEvents.entrySet()) {
            publishUseKafka(entry.getValue(), entry.getKey());
        }
    }
}
