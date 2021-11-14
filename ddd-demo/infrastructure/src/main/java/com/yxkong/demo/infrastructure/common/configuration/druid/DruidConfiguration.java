package com.yxkong.demo.infrastructure.common.configuration.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import com.yxkong.demo.infrastructure.common.constant.DataSourceKey;
import com.yxkong.demo.infrastructure.common.plugin.datasource.DynamicRoutingDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * druid数据源配置
 *
 * @Author: yxkong
 * @Date: 2021/6/2 4:46 下午
 * @version: 1.0
 */
@Configuration
public class DruidConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DruidConfiguration.class);

    private List<Filter> getFilters() {
        List<Filter> filters = new ArrayList<>();
        return filters;
    }

    /**
     * demo库（可能多个）
     * ConfigurationProperties会去自动读取YAML中datasource.loan开头的配置
     *
     * @return DataSource
     */
    @Bean(name = "demoDataSource")
    @ConfigurationProperties(prefix = "datasource.demo")
    public DataSource demoDataSource() {
        log.info("-------------------- demoDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    /**
     * 其他租户库
     *
     * @return
     */
    @Bean(name = "xDemoDataSource")
    @ConfigurationProperties(prefix = "datasource.xdemo")
    public DataSource xDemoDataSource() {
        log.info("-------------------- xDemoDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    /**
     * 动态数据源，根据租户配置
     * @return
     */
    @Bean(name = "demoDynamicDataSource")
    public DataSource demoDynamicDataSource() {
        log.info("-------------------- archetypeDynamicDataSource init ---------------------");
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKey.main.name(), demoDataSource());
        dataSourceMap.put(DataSourceKey.other.name(), xDemoDataSource());
        // 将 wk数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(demoDataSource());
        //设置所有的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

}
