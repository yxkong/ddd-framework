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
 * 密码登录入参
 *
 * @Author: ${author}
 * @Date: 2021/11/26 5:35 PM
 * @version: ${version}
 */
@ApiModel("密码登录")
@Data
@NoArgsConstructor
public class LonginWithPwdCmd extends MobileBase{
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String pwd;
}
