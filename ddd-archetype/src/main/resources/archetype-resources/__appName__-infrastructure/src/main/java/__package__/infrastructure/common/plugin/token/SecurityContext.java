#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.token;

import ${groupId}.common.entity.common.LoginToken;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 11:11 上午
 * @version: ${version}
 */
public interface SecurityContext {
    /**
     * 获取用户登录信息
     * @return
     */
    LoginToken getLoinToken();

}