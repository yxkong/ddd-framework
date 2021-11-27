#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${package}.domain.model.user.PwdObject;
import ${package}.domain.model.user.UserObject;
import lombok.Builder;
import lombok.Getter;

/**
 * 注册上下文
 *
 * @Author: ${author}
 * @Date: 2021/11/15 9:54 PM
 * @version: ${version}
 */
@Builder
@Getter
public class RegisterContext {
    private UserObject userObject;
    private String requestIp;
    private long uuid;
    private PwdObject pwdObject;
    private String proId;
    private String env;
    private Integer status = 1;

}
