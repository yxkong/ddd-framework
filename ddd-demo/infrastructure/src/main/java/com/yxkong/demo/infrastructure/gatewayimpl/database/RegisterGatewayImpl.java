package com.yxkong.demo.infrastructure.gatewayimpl.database;

import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.gateway.RegisterGateway;
import com.yxkong.demo.domain.model.user.AccountEntity;
import com.yxkong.demo.domain.model.user.UserObject;
import com.yxkong.demo.infrastructure.convert.AccountConvert;
import com.yxkong.demo.infrastructure.persistence.entity.demo.AccountDO;
import com.yxkong.demo.infrastructure.persistence.entity.demo.AccountLogDO;
import com.yxkong.demo.infrastructure.persistence.mapper.demoandx.AccountLogMapper;
import com.yxkong.demo.infrastructure.persistence.mapper.demoandx.AccountMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/17 5:51 PM
 * @version: 1.0
 */
@Service
public class RegisterGatewayImpl implements RegisterGateway {
    @Resource
    private AccountMapper accountMapper;
    @Resource
    private AccountLogMapper accountLogMapper;
    @Override
    public boolean isExist(UserObject userObject) {
        AccountDO accountDO = accountMapper.findByMobile(userObject.getMobile());
        if (Objects.nonNull(accountDO)){
            return true;
        }
        return false;
    }

    @Transactional
    @Override
    public AccountEntity register(RegisterContext registerContext) {
        AccountDO accountDO = AccountConvert.account(registerContext);
        accountMapper.insertSelective(accountDO);
        AccountLogDO logDO = AccountConvert.accountLog(registerContext);
        accountLogMapper.insertSelective(logDO);
        return AccountConvert.entity(accountDO);
    }
}
