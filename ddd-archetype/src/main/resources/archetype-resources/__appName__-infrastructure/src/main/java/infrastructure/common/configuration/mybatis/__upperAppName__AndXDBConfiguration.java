#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.mybatis;

import ${package}.infrastructure.common.plugin.mybatis.FillParamInterceptor;
import ${package}.infrastructure.common.plugin.mybatis.ParamInterceptor;
import ${package}.infrastructure.common.plugin.mybatis.ShardingInterceptor;
import ${package}.infrastructure.common.plugin.mybatis.ShardingResultInterceptor;
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
 * @Author: ${author}
 * @Date: 2021/6/3 4:26 下午
 * @version: ${version}
 */
@Configuration
@MapperScan(basePackages = {"${package}.infrastructure.persistence.mapper.${appName}andx"}, sqlSessionFactoryRef = "sqlSessionFactory${upperAppName}AndX")
public class ${upperAppName}AndXDBConfiguration {

    @Resource(name = "${appName}DynamicDataSource")
    private DataSource dataSource;

    /**
     * 设置动态路由的mapper
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactory${upperAppName}AndX")
    public SqlSessionFactory sqlSessionFactory${upperAppName}AndX() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //添加分表插件
        ShardingInterceptor paginationInterceptor = new ShardingInterceptor();
        ShardingResultInterceptor shardingResultHandle = new ShardingResultInterceptor();
        ParamInterceptor paramInterceptor = new ParamInterceptor();
        FillParamInterceptor fillParamInterceptor = new FillParamInterceptor();
        factoryBean.setPlugins(shardingResultHandle, paginationInterceptor, paramInterceptor, fillParamInterceptor);
        factoryBean.setDataSource(dataSource);
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/mapper/${appName}andx/*Mapper.xml"));
        return factoryBean.getObject();
    }

    @Bean(name = "sqlSessionTemplate${upperAppName}AndX")
    public SqlSessionTemplate sqlSessionTemplate${upperAppName}AndX() throws Exception {
        // 使用上面配置的Factory
        return new SqlSessionTemplate(sqlSessionFactory${upperAppName}AndX());
    }
}
