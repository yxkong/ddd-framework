#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.apollo;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfigChangeListener;
import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.logging.LogLevel;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.context.annotation.Configuration;

/**
 * 日志级别监听<br>
 * 用于生产上临时调整某个类的日志级别
 * 配置 logging.level.com.xx.TestService
 * @Author: ${author}
 * @Date: 2021/9/6 11:17 上午
 * @version: ${version}
 */
@EnableApolloConfig
@Configuration
@ConditionalOnClass({Config.class,LoggingSystem.class})
public class LoggerLevelListener {

    private static final Logger logger = LoggerFactory.getLogger(LoggerLevelListener.class);
    /**
     * 日志配置前置
     */
    private static final  String LOGGER_PREFIX = "logging.level.";

    @Autowired
    private LoggingSystem loggingSystem;
    @ApolloConfig
    private Config config;

    @ApolloConfigChangeListener
    private void configChangeListener(ConfigChangeEvent changeEvent){
        /**
         * 过滤所有以 LOGGER_PREFIX 开头的配置
         */
        config.getPropertyNames().stream().filter(key-> StringUtils.containsIgnoreCase(key,LOGGER_PREFIX)).forEach(key->{
            String strLevel = config.getProperty(key, "error");
            final LogLevel logLevel = LogLevel.valueOf(strLevel.toUpperCase());
            loggingSystem.setLogLevel(key.replace(LOGGER_PREFIX,""),logLevel);
            logger.warn("{}:{}",key,strLevel);
        });
    }
}