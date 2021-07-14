package com.yxkong.demo.infrastructure.common.configuration.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
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
     * loan库
     *
     * @return DataSource
     */
    @Bean(name = "loanDataSource")
    @ConfigurationProperties(prefix = "datasource.loan")
    public DataSource loanDataSource() {
        log.info("-------------------- loanDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    /**
     * risk库
     *
     * @return DataSource
     */
    @Bean(name = "riskDataSource")
    @ConfigurationProperties(prefix = "datasource.risk")
    public DataSource riskDataSource() {
        log.info("-------------------- riskDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    @Bean(name = "authDataSource")
    @ConfigurationProperties(prefix = "datasource.auth")
    public DataSource authDataSource() {
        log.info("-------------------- authDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    /**
     * 小x卡库
     *
     * @return
     */
    @Bean(name = "xDataSource")
    @ConfigurationProperties(prefix = "datasource.x")
    public DataSource xDataSource() {
        log.info("-------------------- xDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    @Bean(name = "authRiskDataSource")
    @ConfigurationProperties(prefix = "datasource.auth-risk")
    public DataSource authRiskDataSource() {
        log.info("-------------------- authRiskDataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    @Bean(name = "loanDynamicDataSource")
    public DataSource loanDynamicDataSource() {
        log.info("-------------------- loanDynamicDataSource init ---------------------");
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKey.main.name(), loanDataSource());
        dataSourceMap.put(DataSourceKey.x.name(), xDataSource());
        // 将 wk数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(loanDataSource());
        //设置所有的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * risk动态数据源
     *
     * @return DataSource
     */
    @Bean(name = "riskDynamicDataSource")
    public DataSource riskDynamicDataSource() {
        log.info("-------------------- riskDynamicDataSource init ---------------------");
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKey.main.name(), riskDataSource());
        dataSourceMap.put(DataSourceKey.x.name(), xDataSource());
        dynamicRoutingDataSource.setDefaultTargetDataSource(riskDataSource());
        //设置所有的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * auth动态数据源
     *
     * @return DataSource
     */
    @Bean(name = "authDynamicDataSource")
    public DataSource authDynamicDataSource() {
        log.info("-------------------- authDynamicDataSource init ---------------------");
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKey.main.name(), authDataSource());
        dataSourceMap.put(DataSourceKey.x.name(), xDataSource());
        dynamicRoutingDataSource.setDefaultTargetDataSource(authDataSource());
        //设置所有的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

    /**
     * authRisk动态数据源
     *
     * @return DataSource
     */
    @Bean(name = "authRiskDynamicDataSource")
    public DataSource authRiskDynamicDataSource() {
        log.info("-------------------- authRiskDynamicDataSource init ---------------------");
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKey.main.name(), authRiskDataSource());
        dataSourceMap.put(DataSourceKey.x.name(), xDataSource());
        dynamicRoutingDataSource.setDefaultTargetDataSource(authRiskDataSource());
        //设置所有的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }
}
