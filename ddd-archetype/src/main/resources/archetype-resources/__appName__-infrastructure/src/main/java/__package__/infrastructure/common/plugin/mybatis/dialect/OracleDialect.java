#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.mybatis.dialect;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 5:19 下午
 * @version: ${version}
 */
public class OracleDialect extends BaseDialect {

    /*
     * (non-Javadoc)
     *
     * @see
     * org.mybatis.extend.interceptor.IDialect${symbol_pound}getLimitString(java.lang.String,
     * int, int)
     */
    @Override
    public String getLimitString(String sql, int offset, int limit) {

        sql = sql.trim();
        StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);

        pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");

        pagingSelect.append(sql);

        pagingSelect.append(" ) row_ ) where rownum_ > ").append(offset).append(" and rownum_ <= ")
                .append(offset + limit);

        return pagingSelect.toString();
    }

}