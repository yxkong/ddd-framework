package com.yxkong.demo.infrastructure.common.configuration.mybatis;

import com.yxkong.demo.infrastructure.common.plugin.mybatis.FillParamInterceptor;
import com.yxkong.demo.infrastructure.common.plugin.mybatis.ParamInterceptor;
import com.yxkong.demo.infrastructure.common.plugin.mybatis.ShardingInterceptor;
import com.yxkong.demo.infrastructure.common.plugin.mybatis.ShardingResultInterceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * loan库动态数据源
 *
 * @Author: yxkong
 * @Date: 2021/6/3 4:26 下午
 * @version: 1.0
 */
@Configuration
@MapperScan(basePackages = {"com.yxkong.demo.infrastructure.persistence.mapper.demo"}, sqlSessionFactoryRef = "sqlSessionFactoryDemo")
public class DemoDBConfiguration {

    @Resource(name = "demoDataSource")
    private DataSource dataSource;

    /**
     * 设置只走archetype库的mapper
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactoryDemo")
    public SqlSessionFactory sqlSessionFactoryDemo() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //添加分表插件
        ShardingInterceptor paginationInterceptor = new ShardingInterceptor();
        ShardingResultInterceptor shardingResultHandle = new ShardingResultInterceptor();
        ParamInterceptor paramInterceptor = new ParamInterceptor();
        FillParamInterceptor fillParamInterceptor = new FillParamInterceptor();
        factoryBean.setPlugins(shardingResultHandle, paginationInterceptor, paramInterceptor, fillParamInterceptor);
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/demo/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplateDemo")
    public SqlSessionTemplate sqlSessionTemplateDemo() throws Exception {
        // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactoryDemo());
    }
}
