package com.yxkong.demo.infrastructure.common.configuration;

import com.yxkong.demo.infrastructure.common.filter.HttpTraceLogFilter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yxkong
 * @Date: 2021/6/3 2:03 下午
 * @version: 1.0
 */
@Configuration
@ConditionalOnWebApplication
public class HttpFilterConfiguration {
    @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
    static class ServletTraceFilterConfiguration {

        @Bean
        // @ConditionalOnProperty(name = "onecard.http.trace.enabled", havingValue = "true")
        public HttpTraceLogFilter httpTraceLogFilter(MeterRegistry registry) {
            return new HttpTraceLogFilter(registry);
        }
        //其他的fitler都可以放合理
    }
}