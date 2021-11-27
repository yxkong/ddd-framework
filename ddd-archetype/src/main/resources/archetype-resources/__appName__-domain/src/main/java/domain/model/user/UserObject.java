#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import com.yxkong.common.annotation.DomainValueObject;
import com.yxkong.common.constant.TenantEnum;
import lombok.Getter;

/**
 * 未注册之前的用户值对象
 *
 * @Author: ${author}
 * @Date: 2021/11/15 3:19 PM
 * @version: ${version}
 */
@DomainValueObject
@Getter
public class UserObject {
    /**
     * 用户手机号码
     */
    private String mobile;
    private TenantEnum tenantEnum;


    public UserObject(String mobile, TenantEnum tenantEnum) {
        this.mobile = mobile;
        this.tenantEnum = tenantEnum;
    }
}
