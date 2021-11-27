#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.command.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通过图形验证码发送短信
 *
 * @Author: ${author}
 * @Date: 2021/11/15 10:35 AM
 * @version: ${version}
 */
@ApiModel("图形发送验证码")
@Data
@NoArgsConstructor
public class SmsByCaptchaCmd {
}
