#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import com.yxkong.common.annotation.DomainEntity;
import com.yxkong.common.constant.TenantEnum;
import lombok.Getter;

/**
 * 账户实体
 * ps: 对于我们来说id+ 组合才能唯一确定账户
 * @Author: ${author}
 * @Date: 2021/5/31 4:58 下午
 * @version: ${version}
 */
@DomainEntity
@Getter
public class AccountId {
    private Long id;
    private Long uuid;
    private TenantEnum tenant;
    public AccountId(Long id, Long uuid, TenantEnum tenant) {
        this.id = id;
        this.uuid = uuid;
        this.tenant = tenant;
    }
}