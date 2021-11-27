#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.feign;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignLoggerFactory;

/**
 * 自定义工厂，将feign client对应接口的class传入到自定义Logger中
 *
 * @author ${author}
 * @date 2019/7/19-10:57
 */
public class CustomizedFeignLoggerFactory implements FeignLoggerFactory {

    @Override
    public Logger create(Class<?> type) {
        return new CustomizedFeignLogger(type);
    }
}
