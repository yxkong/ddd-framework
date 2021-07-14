package com.yxkong.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @Author: yxkong
 * @Date: 2021/7/14 6:43 下午
 * @version: 1.0
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.yxkong"})
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class Starter {
    public static void main(String[] args) {
        SpringApplication.run(Starter.class, args);
    }
}