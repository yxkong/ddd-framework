#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wyx
 * @date 2021/10/11
 * @description 钉钉告警
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "ding-talk")
public class DingTalkProperties {
     private String baseUrl;
     private String token;
     private String secret;
}
