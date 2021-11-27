#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.gateway;

import com.yxkong.common.constant.TenantEnum;
import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import ${package}.domain.dto.context.RegisterContext;
import ${package}.domain.model.user.AccountEntity;
import ${package}.domain.model.user.AccountId;
import ${package}.domain.model.user.UserObject;

import java.util.List;

/**
 * 注册网关
 *
 * @Author: ${author}
 * @Date: 2021/11/17 3:03 PM
 * @version: ${version}
 */
public interface AccountGateway {

    /**
     * 注册
     * @param registerContext
     * @return
     */
    AccountEntity register(RegisterContext registerContext);

    /**
     * 记录account log
     * @param entity
     * @return
     */
    int accountLog(AccountEntity entity,String requestIp,String env);

    /**
     * 查询用户实体
     * @param userObject
     * @return
     */
    AccountEntity findByMobile(UserObject userObject);
    /**
     * 查询用户实体
     * @param accountId
     * @return
     */
    AccountEntity findByUuid(AccountId accountId);

    /**
     * 密码是否正确
     * @param entity
     * @return
     */
    boolean pwdIsTrue(AccountEntity entity,String pwd);

    /**
     * 生成token
     * @param entity
     * @return
     */
    LoginToken generatorToken(AccountEntity entity,Integer loginType,String proId);


    /**
     * 获取accountLog
     * @param uuid
     * @return
     */
    ResultBean accountLog(Long uuid);
}
