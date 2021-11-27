//package com.yxkong.demo;
//
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.context.TestConfiguration;
//import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestExecutionListeners;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
//
///**
// * <TODO>
// *
// * @Author: yxkong
// * @Date: 2021/11/25 3:46 PM
// * @version: 1.0
// */
//
//@RunWith(SpringRunner.class)
//@ActiveProfiles("test")
//@SpringBootTest(classes = TestConfiguration.class, properties = {
//        "onecard.kafka.service.enable=false",
//        "onecard.swagger.show=false",
//        "eureka.client.enabled=false",
//        "spring.cloud.config.enabled=false",
//        "spring.zipkin.enabled=false",
//        "spring.cloud.config.discovery.enabled=false"})
//@TestExecutionListeners({MockitoTestExecutionListener.class, DependencyInjectionTestExecutionListener.class,
//        TransactionDbUnitTestExecutionListener.class})
//@DbUnitConfiguration(dataSetLoader = DataSetLoader.class, databaseConnection = "mysqlDatabaseConnection")
//@MockBean(classes = {KafkaListenerAnnotationBeanPostProcessor.class, KafkaListenerEndpointRegistry.class})
//
//public class BaseSpringContextTest {
//}
