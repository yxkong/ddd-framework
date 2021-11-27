#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${package}.domain.model.user.PwdObject;
import ${package}.domain.model.user.UserObject;
import lombok.Builder;
import lombok.Getter;

/**
 * 登录上下文
 *
 * @Author: ${author}
 * @Date: 2021/11/26 2:20 PM
 * @version: ${version}
 */
@Builder
@Getter
public class LoginContext {
    private UserObject userObject;
    private String requestIp;
    private String  pwd;
    /**
     * 1密码登录
     */
    private Integer loginType;
    private String proId;
    private String env;

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }
}
