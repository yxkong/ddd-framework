#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import ${groupId}.common.entity.common.LoginToken;
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
    public DistributeContext(LoginToken loginToken) {
        super(loginToken.getAccountId(), loginToken.getCustomerId(), loginToken.getTenantId());
        this.mobile = loginToken.getMobile();
    }
}