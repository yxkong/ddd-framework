package com.yxkong.demo.domain.service;

import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.util.ResultBeanUtil;
import com.yxkong.demo.domain.constant.DomainResultEnum;
import com.yxkong.demo.domain.dto.context.LoginContext;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.gateway.AccountGateway;
import com.yxkong.demo.domain.model.user.AccountEntity;
import lombok.Builder;

import java.util.Objects;

/**
 * 注册领域服务
 *
 * @Author: yxkong
 * @Date: 2021/11/17 2:54 PM
 * @version: 1.0
 */
@DomainService
@Builder
public class AccountService {



    AccountGateway accountGateway;
    /**
     * 注册服务
     * @param context
     * @return
     */
    public ResultBean<AccountEntity> register(RegisterContext context){
        AccountEntity accountEntity = accountGateway.findByMobile(context.getUserObject());
        if (Objects.isNull(accountEntity)){
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
        LoginToken token = accountGateway.generatorToken(accountEntity, context.getLoginType(), context.getProId());
        return ResultBeanUtil.success("登录成功！",token.getToken());
    }
}
