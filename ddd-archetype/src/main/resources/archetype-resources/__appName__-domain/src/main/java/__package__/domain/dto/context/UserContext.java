#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${package}.domain.model.user.AccountId;
import ${package}.domain.model.user.CustomerId;
import lombok.Getter;

/**
 * 用户上下文
 *
 * @Author: ${author}
 * @Date: 2021/6/3 6:58 下午
 * @version: ${version}
 */
@Getter
public class UserContext {
    private AccountId accountId;
    private CustomerId customerId;

    public UserContext(Long accountId, Long customerId, Integer tenantId) {
        this.accountId = new AccountId(accountId, tenantId);
        this.customerId = new CustomerId(customerId, tenantId);
    }
}