#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 配置网关
 *
 * @Author: ${author}
 * @Date: 2021/6/3 7:17 下午
 * @version: ${version}
 */
package ${package}.domain.gateway;

/**
 * 〈配置网关〉
 *
 * @author ducongcong
 * @create 2021/6/3
 * @since ${version}.0
 */
public interface ConfigGateway {
    /**
     * 根据key查询配置
     * ps: 按理说不应该关注是哪个key，为了通用
     * @param key
     * @return
     */
    String query(String key, String defaultValue);
}
