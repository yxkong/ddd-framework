#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import com.yxkong.common.annotation.AggregateRoot;
import com.yxkong.common.annotation.DomainValueObject;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 账户领域对象
 *
 * @Author: ${author}
 * @Date: 2021/5/31 6:13 下午
 * @version: ${version}
 */
@AggregateRoot
@Getter
public class AccountEntity {
    public AccountEntity(AccountId accountId, UserObject user, AccountStatusEnum accountStatus, String proId) {
        this.accountId = accountId;
        this.user = user;
        this.accountStatus = accountStatus;
        this.proId = proId;
    }

    private AccountId accountId;

    /**
     * 用户对象
     */
    private UserObject user;
    /**
     * 用户状态
     */
    private AccountStatusEnum accountStatus;
    /**
     * 用户注册渠道
     */
    private String proId;
    /**
     * 注册时间
     */
    private Date registerTime;

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    /**
     * 密码对象
     */
    private PwdObject pwdObject;

    public void setPwdObject(String salt,String md5pwd) {
        this.pwdObject = new PwdObject(salt,md5pwd);
    }
}
