#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.kafka;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaConsumerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.DefaultKafkaProducerFactoryCustomizer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.LoggingProducerListener;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;

/**
 * 业务kafka
 *
 * @Author: ${author}
 * @Date: 2021/6/15 2:36 下午
 * @version: ${version}
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.kafka.biz", name = "enabled", havingValue = "true")
public class BizKafkaAutoConfiguration {
    private BizKafkaProperties bizProperties;

    public BizKafkaAutoConfiguration(BizKafkaProperties bizProperties) {
        this.bizProperties = bizProperties;
    }

    @Bean(name = "bizKafkaTemplate")
    public KafkaTemplate<Object, Object> bizKafkaTemplate(ProducerFactory<Object, Object> bizKafkaProducerFactory,
                                                          ProducerListener<Object, Object> bizKafkaProducerListener,
                                                          ObjectProvider<RecordMessageConverter> messageConverter) {
        KafkaTemplate<Object, Object> kafkaTemplate = new KafkaTemplate<>(bizKafkaProducerFactory);
        messageConverter.ifUnique(kafkaTemplate::setMessageConverter);
        kafkaTemplate.setProducerListener(bizKafkaProducerListener);
        kafkaTemplate.setDefaultTopic(kafkaProperties().getTemplate().getDefaultTopic());
        return kafkaTemplate;
    }

    /**
     * 生产者的配置
     */
    @Bean(name = "bizKafkaProducerListener")
    public ProducerListener<Object, Object> bizKafkaProducerListener() {
        return new LoggingProducerListener<>();
    }

    @Bean(name = "bizKafkaProducerFactory")
    public ProducerFactory<Object, Object> bizKafkaProducerFactory(ObjectProvider<DefaultKafkaProducerFactoryCustomizer> customizers) {
        DefaultKafkaProducerFactory<Object, Object> factory = new DefaultKafkaProducerFactory<>(kafkaProperties().buildProducerProperties());
        String transactionIdPrefix = kafkaProperties().getProducer().getTransactionIdPrefix();
        if (transactionIdPrefix != null) {
            factory.setTransactionIdPrefix(transactionIdPrefix);
        }
        customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));
        return factory;
    }

    @Bean(name = "bizKafkaListenerContainerFactoryConfigurer")
    public ConcurrentKafkaListenerContainerFactoryConfigurer bizKafkaListenerContainerFactoryConfigurer(
            KafkaTemplate<Object, Object> bizKafkaTemplate, ObjectProvider<RecordMessageConverter> messageConverter) {

        ConcurrentKafkaListenerContainerFactoryConfigurer configurer = new ConcurrentKafkaListenerContainerFactoryConfigurer();

        configurer.setKafkaProperties(this.kafkaProperties());
        configurer.setMessageConverter(messageConverter.getIfUnique());
        configurer.setReplyTemplate(bizKafkaTemplate);
        return configurer;
    }

    @Bean(name = "bizKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<?, ?> bizKafkaListenerContainerFactory(
            ConcurrentKafkaListenerContainerFactoryConfigurer bizKafkaListenerContainerFactoryConfigurer,
            ConsumerFactory<Object, Object> bizKafkaConsumerFactory) {
        ConcurrentKafkaListenerContainerFactory<Object, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        bizKafkaListenerContainerFactoryConfigurer.configure(factory, bizKafkaConsumerFactory);
        return factory;
    }

    /**
     * 消费者的配置
     *
     * @param customizers
     * @return
     */
    @Bean(name = "bizKafkaConsumerFactory")
    public ConsumerFactory<?, ?> bizKafkaConsumerFactory(ObjectProvider<DefaultKafkaConsumerFactoryCustomizer> customizers) {
        DefaultKafkaConsumerFactory<Object, Object> factory = new DefaultKafkaConsumerFactory<>(kafkaProperties().buildConsumerProperties());
        customizers.orderedStream().forEach((customizer) -> customizer.customize(factory));
        return factory;
    }

    @Bean(name = "bizKafkaAdmin")
    public KafkaAdmin bizKafkaAdmin() {
        KafkaAdmin kafkaAdmin = new KafkaAdmin(kafkaProperties().buildAdminProperties());
        kafkaAdmin.setFatalIfBrokerNotAvailable(kafkaProperties().getAdmin().isFailFast());
        return kafkaAdmin;
    }

    private KafkaProperties kafkaProperties() {
        KafkaProperties kafkaProperties = this.bizProperties.getKafkaProperties();
        kafkaProperties.setBootstrapServers(this.bizProperties.getBizBootstrapServers());
        return kafkaProperties;
    }
}