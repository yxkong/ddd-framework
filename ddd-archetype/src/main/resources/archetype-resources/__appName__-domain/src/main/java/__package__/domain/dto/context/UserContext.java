#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${groupId}.common.constant.TenantEnum;
import ${package}.domain.model.user.AccountId;
import ${package}.domain.model.user.CustomerId;
import lombok.Getter;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/11/15 6:42 PM
 * @version: ${version}
 */
@Getter
public class UserContext {
    private AccountId accountId;
    private CustomerId customerId;

    public UserContext(AccountId accountId, CustomerId customerId) {
        this.accountId = accountId;
        this.customerId = customerId;
    }

    public UserContext(Long accountId, Long customerId, TenantEnum tenant) {
        this.accountId = new AccountId(accountId,null,tenant);
        this.customerId = new CustomerId(customerId,tenant);
    }
}
