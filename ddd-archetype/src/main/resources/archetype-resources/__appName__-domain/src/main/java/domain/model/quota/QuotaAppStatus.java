#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.quota;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 6:18 下午
 * @version: ${version}
 */
public enum QuotaAppStatus {
    INIT,
    WAITCHECK,
    MANUAL,
    BACK,
    PASS,
    REFUSE;
}
