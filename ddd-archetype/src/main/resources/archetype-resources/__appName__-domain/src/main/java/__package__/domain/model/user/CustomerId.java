#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import ${groupId}.common.annotation.DomainEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * 用户实体
 * ps: 对于我们来说id+ 组合才能唯一确定用户
 *
 * @Author: ${author}
 * @Date: 2021/5/31 4:59 下午
 * @version: ${version}
 */
@Data
@DomainEntity
@NoArgsConstructor
public class CustomerId {

    private Long id;
    private Integer tenantId;

    public CustomerId(Long id, Integer tenantId) {
        this.id = id;
        this.tenantId = tenantId;
    }

    public boolean isRealName(){
        if (Objects.isNull(this.id) || this.id == 0L){
            return false;
        }
        return true;
    }
}