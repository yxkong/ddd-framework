#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.command;

import ${package}.infrastructure.persistence.entity.${appName}.AccountDO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/10/28 12:38 PM
 * @version: ${version}
 */
@Data
@NoArgsConstructor
@ApiModel("Acccount保存参数")
public class AccountSaveCommand {
    @NotEmpty
    @Length(min = 11, max = 11)
    @ApiModelProperty(value = "手机号", required = true)
    private String mobile;

    @NotNull
    @Range(min = 1000, max = 3000, message = "range在[1000,3000]之间")
    @ApiModelProperty(value = "租户id", required = true)
    private Integer tenantId;
    //偷懒了
    public AccountDO getAccountDo() {
        AccountDO accountDO = new AccountDO();
        accountDO.setAccountName(this.getMobile());
        accountDO.setTenantId(this.tenantId);
        return accountDO;
    }
}
