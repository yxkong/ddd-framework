package com.yxkong.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 后续根据实际需要，将用户适配层和应用层拆分
 * @Author: yxkong
 * @Date: 2021/6/1 12:52 下午
 * @version: 1.0
 */
@Slf4j
@SpringBootApplication(scanBasePackages = {"com.yxkong"})
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
@Component
public class DemoStarter {

    public static void main(String[] args) {
        SpringApplication.run(DemoStarter.class, args);
    }

    /**
     * 该类可用于SpringBoot应用启动后提前做一些初始化操作，比如缓存数据，多个可以使用@Ordered排序
     */
    @Bean
    public ApplicationRunner engineStarted() {
        // ApplicationArguments 参数获取形如：--name=zhangsan --gender=nan 格式的命令行参数
        return args -> {
            log.info("Engine started...");
             for(String argKey : args.getOptionNames()) {
                 log.info("参数key: " + argKey+"，参数值：" + args.getOptionValues(argKey));
             }
        };
    }
}
