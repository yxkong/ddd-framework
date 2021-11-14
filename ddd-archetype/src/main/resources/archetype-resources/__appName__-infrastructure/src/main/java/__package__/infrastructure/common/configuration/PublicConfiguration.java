#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration;

import ${package}.infrastructure.common.property.IdWorkerProperties;
import ${package}.infrastructure.common.util.IdWorker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ${author}
 * @date 2021-06-07 16:19
 */
@Configuration
public class PublicConfiguration {

    /**
     * 万卡统一的分布式id生成算法
     *
     * @param idWorkerProperties
     * @return
     * @throws Exception
     */
    @Bean
    public IdWorker idWorker(IdWorkerProperties idWorkerProperties) throws Exception {
        List<String> cluster = idWorkerProperties.getCluster();
        int skip = idWorkerProperties.getSkip();
        if (cluster == null) {
            cluster = new ArrayList<>();
        }
        return new IdWorker(cluster, skip);
    }

     //@Bean
     //@Primary
     //public ObjectMapper objectMapper() {
     //    return Jackson2ObjectMapperBuilder.json().serializationInclusion(JsonInclude.Include.NON_NULL)
     //            .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
     //            .timeZone(TimeZone.getTimeZone("Asia/Shanghai")).modules(new JavaTimeModule())
     //            .build();
     //}
}
