#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.mybatis.dialect;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 5:15 下午
 * @version: ${version}
 */
public abstract class BaseDialect {

    public static enum Type {
        /**
         * 数据库方言枚举
         */
        MYSQL,
        ORACLE
    }

    public abstract String getLimitString(String sql, int skipResults, int maxResults);

}