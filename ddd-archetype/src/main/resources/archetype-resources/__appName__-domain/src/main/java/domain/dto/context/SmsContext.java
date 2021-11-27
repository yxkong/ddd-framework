#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${package}.domain.model.user.UserObject;
import lombok.Getter;

/**
 * 短信发送上下问
 *
 * @Author: ${author}
 * @Date: 2021/11/15 3:32 PM
 * @version: ${version}
 */
@Getter
public class SmsContext {
    private UserObject user;
    private String requestIp;
    private String verifyCode;
    private Integer smsType;
    private String message;
    private String proId;

    public SmsContext(UserObject user, String requestIp,String verifyCode,Integer smsType) {
        this.user = user;
        this.requestIp = requestIp;
        this.verifyCode = verifyCode;
        this.smsType = smsType;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
