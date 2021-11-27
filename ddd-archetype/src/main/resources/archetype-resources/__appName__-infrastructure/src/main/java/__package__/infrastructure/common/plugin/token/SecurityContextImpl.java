#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.token;

import ${groupId}.common.entity.common.LoginToken;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 11:30 上午
 * @version: ${version}
 */
public class SecurityContextImpl implements SecurityContext{
    private LoginToken loginToken;

    public SecurityContextImpl(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public SecurityContextImpl() {
    }

    @Override
    public LoginToken getLoinToken() {
        return this.loginToken;
    }
    public void setLoginToken(LoginToken loginToken){
        this.loginToken = loginToken;
    }
}