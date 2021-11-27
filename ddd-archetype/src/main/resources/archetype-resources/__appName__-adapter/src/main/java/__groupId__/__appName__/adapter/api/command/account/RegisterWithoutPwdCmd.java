#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.command.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 无密码注册
 *
 * @Author: ${author}
 * @Date: 2021/11/15 10:36 AM
 * @version: ${version}
 */
@ApiModel("无密码注册")
@Data
@NoArgsConstructor
public class RegisterWithoutPwdCmd extends MobileBase{
    @NotEmpty(message = "短信验证码不能为空")
    @ApiModelProperty("短信验证码")
    private String verifyCode;



}
