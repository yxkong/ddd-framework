#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.context.account;

import ${groupId}.common.constant.TenantEnum;
import ${package}.domain.model.user.UserObject;
import lombok.Getter;

import java.util.Objects;

/**
 * 注册上下文
 *
 * @Author: ${author}
 * @Date: 2021/11/15 6:45 PM
 * @version: ${version}
 */
@Getter
public class RegisterAppContext {
    private UserObject user;
    private String  verifyCode;
    private String pwd;
    /**
     * 0 无密码，1有密码
     */
    private Integer registerType = 0;
    private String requestIp;
    /**
     * 来源渠道
     */
    private String proId;
    /**
     * 环境
     */
    private String env;

    public RegisterAppContext(Integer tenantId, String mobile, String verifyCode, String requestIp, String pwd,String proId,String env){
        TenantEnum tenantEnum = TenantEnum.get(tenantId);
        this.verifyCode = verifyCode;
        this.user = new UserObject(mobile,tenantEnum);
        this.requestIp = requestIp;
        this.pwd = pwd;
        this.proId = proId;
        this.env = env;
        if (Objects.nonNull(this.pwd)){
            this.registerType = 1;
        }
    }

}
