#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.resp;

/**
 * 分发路径
 *
 * @Author: ${author}
 * @Date: 2021/6/3 6:12 下午
 * @version: ${version}
 */
public enum DistributePath {
    flow,
    list,
    waiting,
    source;
}
