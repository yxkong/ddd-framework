#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.service;

import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.util.ResultBeanUtil;
import ${package}.domain.constant.DomainResultEnum;
import ${package}.domain.dto.context.LoginContext;
import ${package}.domain.dto.context.RegisterContext;
import ${package}.domain.gateway.AccountGateway;
import ${package}.domain.model.user.AccountEntity;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

/**
 * 注册领域服务
 *
 * @Author: ${author}
 * @Date: 2021/11/17 2:54 PM
 * @version: ${version}
 */
@DomainService
@Builder
@Slf4j
public class AccountService {



    AccountGateway accountGateway;
    /**
     * 注册服务
     * @param context
     * @return
     */
    public ResultBean<AccountEntity> register(RegisterContext context){
        AccountEntity accountEntity = accountGateway.findByMobile(context.getUserObject());
        if (Objects.nonNull(accountEntity)){
            return ResultBeanUtil.result(DomainResultEnum.REGISTERED);
        }
        accountEntity = accountGateway.register(context);
        if (Objects.nonNull(accountEntity)){
            return ResultBeanUtil.success("注册成功！",accountEntity);
        }
        return ResultBeanUtil.fail("注册失败！",null);
    }

    public ResultBean login(LoginContext context) {
        AccountEntity accountEntity = accountGateway.findByMobile(context.getUserObject());
        if (Objects.isNull(accountEntity)){
            return ResultBeanUtil.result(DomainResultEnum.LONGINFAIL);
        }
        boolean isTrue = true;
        if (context.getLoginType() == 1){
            isTrue = accountGateway.pwdIsTrue(accountEntity, context.getPwd());
        }
        if (!isTrue){
            return ResultBeanUtil.result(DomainResultEnum.LONGINFAIL);
        }
        try {
            accountGateway.accountLog(accountEntity, context.getRequestIp(), context.getEnv());
        } catch (Exception e) {
            log.info("记录{}登录日志异常！",accountEntity.getAccountId().getUuid());
        }
        LoginToken token = accountGateway.generatorToken(accountEntity, context.getLoginType(), context.getProId());
        return ResultBeanUtil.success("登录成功！",token.getToken());
    }
}
