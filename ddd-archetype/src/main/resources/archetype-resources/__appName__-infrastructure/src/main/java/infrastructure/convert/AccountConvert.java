#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.convert;

import com.yxkong.common.constant.TenantEnum;
import com.yxkong.common.entity.common.LoginToken;
import ${package}.domain.dto.context.RegisterContext;
import ${package}.domain.model.user.AccountEntity;
import ${package}.domain.model.user.AccountId;
import ${package}.domain.model.user.AccountStatusEnum;
import ${package}.domain.model.user.UserObject;
import ${package}.infrastructure.common.util.DateUtils;
import ${package}.infrastructure.common.util.StringUtils;
import ${package}.infrastructure.persistence.entity.${appName}.AccountDO;
import ${package}.infrastructure.persistence.entity.${appName}.AccountLogDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.Objects;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/11/17 6:15 PM
 * @version: ${version}
 */
public class AccountConvert {
    public static AccountDO account(RegisterContext context){
        AccountDO accountDO = new AccountDO();
        Date date = new Date();
//        BeanUtils.copyProperties(registerContext,accountDO);
        accountDO.setMobile(context.getUserObject().getMobile());
        accountDO.setProId(context.getProId());
        accountDO.setPwd(context.getPwdObject().getMd5Pwd());
        accountDO.setSalt(context.getPwdObject().getSalt());
        accountDO.setStatus(context.getStatus());
        accountDO.setUuid(context.getUuid());
        accountDO.setStatus(AccountStatusEnum.ON.getStatus());
        accountDO.setCreateTime(date);
        accountDO.setUpdatedTime(date);
        return accountDO;
    }

    public static AccountEntity entity(AccountDO accountDO){
        if (Objects.isNull(accountDO)){
            return null;
        }
        TenantEnum  tenant= TenantEnum.get(accountDO.getTenantId());
        AccountEntity  entity=  new AccountEntity(new AccountId(accountDO.getId(), accountDO.getUuid(), tenant),new UserObject(accountDO.getMobile(),tenant), AccountStatusEnum.get(accountDO.getStatus()),accountDO.getProId());
        entity.setPwdObject(accountDO.getSalt(),accountDO.getPwd());
        entity.setRegisterTime(accountDO.getCreateTime());
        return entity;
    }
    public static AccountLogDO accountLog(RegisterContext context){
        return  AccountConvert.accountLog(context.getUuid(),context.getProId(),1,context.getRequestIp(),context.getEnv());
    }
    public static AccountLogDO accountLog(AccountEntity entity,String requestIp,String env){
        return AccountConvert.accountLog(entity.getAccountId().getUuid(),entity.getProId(),2,requestIp,env);
    }
    public static AccountLogDO accountLog(Long uuid,String proId,Integer bizType,String requestIp,String env){
        AccountLogDO log = new AccountLogDO();
        log.setUuid(uuid);
        log.setProId(proId);
        log.setEnv(env);
        log.setBizType(bizType);
        log.setRequestIp(requestIp);
        return log;
    }

    public static LoginToken token(String token,AccountEntity entity,Integer loginType,String proId) {
        LoginToken loginToken = new LoginToken(token,entity.getUser().getMobile(),entity.getAccountId().getId(),entity.getAccountId().getUuid(),entity.getAccountId().getTenant().getTenantId());
        loginToken.setLoginTime(DateUtils.getNowTime());
        loginToken.setLoginType(loginType);
        loginToken.setProId(entity.getProId());
        loginToken.setLoginProId(proId);
        loginToken.setToken(token);
        loginToken.setProId(entity.getProId());
        loginToken.setTenantId(entity.getAccountId().getTenant().getTenantId());
        loginToken.setRegisterTime(DateUtils.formateDateStr(entity.getRegisterTime()));
        return loginToken;
    }
}
