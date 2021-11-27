#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import com.yxkong.common.annotation.DomainEntity;
import com.yxkong.common.constant.TenantEnum;
import ${package}.domain.model.BaseModel;
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
public class CustomerId extends BaseModel {
    private Long id;
    private TenantEnum tenant;
    public CustomerId(Long id,  TenantEnum tenant) {
        this.id = id;
        this.tenant = tenant;
    }

    public boolean isRealName(){
        if (Objects.isNull(this.id) || this.id == 0L){
            return false;
        }
        return true;
    }
}