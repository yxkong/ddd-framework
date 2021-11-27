#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.druid;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.pool.DruidDataSource;
import ${package}.infrastructure.common.constant.DataSourceKey;
import ${package}.infrastructure.common.plugin.datasource.DynamicRoutingDataSource;
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
 * @Author: ${author}
 * @Date: 2021/6/2 4:46 下午
 * @version: ${version}
 */
@Configuration
public class DruidConfiguration {

    private static final Logger log = LoggerFactory.getLogger(DruidConfiguration.class);

    private List<Filter> getFilters() {
        List<Filter> filters = new ArrayList<>();
        return filters;
    }

    /**
     * ${appName}库（可能多个）
     * ConfigurationProperties会去自动读取YAML中datasource.loan开头的配置
     *
     * @return DataSource
     */
    @Bean(name = "${appName}DataSource")
    @ConfigurationProperties(prefix = "datasource.${appName}")
    public DataSource ${appName}DataSource() {
        log.info("-------------------- ${appName}DataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    /**
     * 其他租户库
     *
     * @return
     */
    @Bean(name = "x${upperAppName}DataSource")
    @ConfigurationProperties(prefix = "datasource.x${appName}")
    public DataSource x${upperAppName}DataSource() {
        log.info("-------------------- x${upperAppName}DataSource init ---------------------");
        DruidDataSource dataSource = DataSourceBuilder.create().type(DruidDataSource.class).build();
        dataSource.setProxyFilters(getFilters());
        return dataSource;
    }

    /**
     * 动态数据源，根据租户配置
     * @return
     */
    @Bean(name = "${appName}DynamicDataSource")
    public DataSource ${appName}DynamicDataSource() {
        log.info("-------------------- ${appName}DynamicDataSource init ---------------------");
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object, Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put(DataSourceKey.main.name(), ${appName}DataSource());
        dataSourceMap.put(DataSourceKey.other.name(), x${upperAppName}DataSource());
        // 将 wk数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(${appName}DataSource());
        //设置所有的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        return dynamicRoutingDataSource;
    }

}
