#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${groupId}.common.constant.TenantEnum;
import ${groupId}.common.entity.common.LoginToken;
import ${package}.domain.model.user.CustomerId;
import lombok.Getter;

/**
 * 分发上下文
 *
 * @Author: ${author}
 * @Date: 2021/6/3 6:15 下午
 * @version: ${version}
 */
@Getter
public class DistributeContext extends UserContext {
	private String mobile;
    private CustomerId customerId;
    public DistributeContext(LoginToken token) {
        super(token.getAccountId(), token.getCustomerId(),TenantEnum.get(token.getTenantId()));
        this.mobile = token.getMobile();
        this.customerId = new CustomerId(token.getCustomerId(),  TenantEnum.get(token.getTenantId()));
    }
}