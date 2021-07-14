package com.yxkong.demo.infrastructure.common.configuration.feign;

import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.FeignLoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yxkong
 * @date 2021/6/30
 */
@Configuration
public class FeignConfiguration {

    @Bean
    public FeignLoggerFactory feignLoggerFactory() {
        return new CustomizedFeignLoggerFactory();
    }

    /**
     * 创建Feign请求拦截器，在发送请求前设置认证的token,各个微服务将token设置到环境变量中来达到通用
     * 通过feign请求统一添加到header
     */
    @Bean
    public FeignBasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new FeignBasicAuthRequestInterceptor();
    }

    public class FeignBasicAuthRequestInterceptor implements RequestInterceptor {

        public FeignBasicAuthRequestInterceptor() {
        }

        @Override
        public void apply(RequestTemplate template) {
        	LoginToken loginToken = LoginTokenUtil.getLoginToken();
        	if(loginToken != null){
        		template.header("token", loginToken.getToken());
                template.header("proId", loginToken.getProId());
        	}
            template.header("tenantId", String.valueOf(LoginTokenUtil.getTenantId()));
        }
    }
}
