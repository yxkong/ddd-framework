#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.sleuth;

import ${package}.infrastructure.common.configuration.sleuth.filter.TracingFilter;
import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: ${author}
 * @Date: 2021/7/27 3:15 下午
 * @version: ${version}
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ddd.sleuth")
@ConditionalOnProperty(name = "ddd.sleuth.enabled", havingValue = "true", matchIfMissing = true)
public class SleuthFilterConfiguration {
    private boolean enabled;

    @Bean
    public FilterRegistrationBean<TracingFilter> tracingFilterRegistrationBean() {
        FilterRegistrationBean<TracingFilter> filterRegistrationBean = new FilterRegistrationBean<TracingFilter>();
        filterRegistrationBean.setFilter(new TracingFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("TracingInterceptor");
        return filterRegistrationBean;
    }
}