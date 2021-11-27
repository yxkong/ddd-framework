#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import com.yxkong.common.annotation.DomainValueObject;
import lombok.Getter;

/**
 * 密码值对象
 *
 * @Author: ${author}
 * @Date: 2021/11/26 10:22 AM
 * @version: ${version}
 */
@DomainValueObject
@Getter
public class PwdObject {
    private String salt;
    private String md5Pwd;

    public PwdObject(String salt, String md5Pwd) {
        this.salt = salt;
        this.md5Pwd = md5Pwd;
    }
}
