#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.gatewayimpl.database;

import ${groupId}.common.entity.common.LoginToken;
import ${groupId}.common.entity.dto.ResultBean;
import ${groupId}.common.util.ResultBeanUtil;
import com.${author}.${appName}.domain.dto.context.RegisterContext;
import com.${author}.${appName}.domain.gateway.AccountGateway;
import com.${author}.${appName}.domain.model.user.AccountEntity;
import com.${author}.${appName}.domain.model.user.AccountId;
import com.${author}.${appName}.domain.model.user.PwdObject;
import com.${author}.${appName}.domain.model.user.UserObject;
import ${package}.infrastructure.common.util.*;
import ${package}.infrastructure.convert.AccountConvert;
import ${package}.infrastructure.persistence.entity.${appName}.AccountDO;
import ${package}.infrastructure.persistence.entity.${appName}.AccountLogDO;
import ${package}.infrastructure.persistence.entity.${appName}.TokenIdxDO;
import ${package}.infrastructure.persistence.mapper.${appName}andx.AccountLogMapper;
import ${package}.infrastructure.persistence.mapper.${appName}andx.AccountMapper;
import ${package}.infrastructure.persistence.mapper.${appName}andx.TokenIdxMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 账户网关
 * @Author: ${author}
 * @Date: 2021/11/17 5:51 PM
 * @version: ${version}
 */
@Service
@Slf4j
public class AccountGatewayImpl implements AccountGateway {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountLogMapper accountLogMapper;

    @Resource
    private TokenIdxMapper tokenIdxMapper;

    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Transactional
    @Override
    public AccountEntity register(RegisterContext registerContext) {
        AccountDO accountDO = AccountConvert.account(registerContext);
        accountMapper.insertSelective(accountDO);
        AccountLogDO logDO = AccountConvert.accountLog(registerContext);
        accountLogMapper.insertSelective(logDO);
        return AccountConvert.entity(accountDO);
    }

    @Override
    public int accountLog(AccountEntity entity,String requestIp,String env) {
        AccountLogDO logDO = AccountConvert.accountLog(entity,requestIp,env);
        return accountLogMapper.insertSelective(logDO);
    }

    @Override
    public AccountEntity findByMobile(UserObject userObject) {
        AccountDO account = accountMapper.findByMobile(userObject.getMobile());
        return AccountConvert.entity(account);
    }

    @Override
    public AccountEntity findByUuid(AccountId accountId) {
        AccountDO account = accountMapper.findByUuid(accountId.getUuid());
        return AccountConvert.entity(account);
    }

    @Override
    public boolean pwdIsTrue(AccountEntity entity,String pwd) {
        if (Objects.isNull(entity)){
            return false;
        }
        PwdObject pwdObject = entity.getPwdObject();
        if (MD5Utils.getSaltMD5(pwd,pwdObject.getSalt()).equals(pwdObject.getMd5Pwd())){
            return true;
        }
        return false;
    }

    @Override
    public LoginToken generatorToken(AccountEntity entity,Integer loginType,String proId) {
        String token = StringUtils.randomUUIDRmLine();
        LoginToken loginToken = AccountConvert.token(token,entity,loginType,proId);
        redisTemplate.opsForValue().set(LoginTokenUtil.getKey(token), JsonUtils.toJson(loginToken));
        redisTemplate.expire(token,7, TimeUnit.DAYS);
        LoginTokenUtil.reloadLoginToken(loginToken);
        try {
            //偷懒
            TokenIdxDO tokenIdxDO = new TokenIdxDO();
            tokenIdxDO.setToken(token);
            tokenIdxDO.setMobile(entity.getUser().getMobile());
            tokenIdxDO.setUuid(entity.getAccountId().getUuid());
            tokenIdxDO.setCreateTime(new Date());
            tokenIdxMapper.insertSelective(tokenIdxDO);
        } catch (Exception e) {
            log.error("记录用户{}的token：{}异常！",entity.getAccountId().getUuid(),token);
        }
        return loginToken;
    }

    @Override
    public ResultBean accountLog(Long uuid) {
        List<AccountLogDO> list =  accountLogMapper.findByUuid(uuid);
        return ResultBeanUtil.success("",list);
    }
}
