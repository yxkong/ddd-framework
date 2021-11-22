package com.yxkong.demo.infrastructure.convert;

import com.yxkong.common.constant.TenantEnum;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.model.user.AccountEntity;
import com.yxkong.demo.domain.model.user.AccountId;
import com.yxkong.demo.domain.model.user.AccountStatusEnum;
import com.yxkong.demo.domain.model.user.UserObject;
import com.yxkong.demo.infrastructure.persistence.entity.demo.AccountDO;
import com.yxkong.demo.infrastructure.persistence.entity.demo.AccountLogDO;
import org.springframework.beans.BeanUtils;

import java.util.Date;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/17 6:15 PM
 * @version: 1.0
 */
public class AccountConvert {
    public static AccountDO account(RegisterContext context){
        AccountDO accountDO = new AccountDO();
        Date date = new Date();
//        BeanUtils.copyProperties(registerContext,accountDO);
        accountDO.setMobile(context.getUserObject().getMobile());
        accountDO.setProId(context.getProId());
        accountDO.setPwd(context.getPwd());
        accountDO.setSalt(context.getSalt());
        accountDO.setStatus(context.getStatus());
        accountDO.setUuid(context.getUuid());
        accountDO.setCreateTime(date);
        accountDO.setUpdatedTime(date);
        return accountDO;
    }

    public static AccountEntity entity(AccountDO accountDO){
        TenantEnum  tenant= TenantEnum.get(accountDO.getTenantId());
        return new AccountEntity(new AccountId(accountDO.getId(), accountDO.getUuid(), tenant),new UserObject(accountDO.getMobile(),tenant), AccountStatusEnum.get(accountDO.getStatus()),accountDO.getProId());
    }
    public static AccountLogDO accountLog(RegisterContext context){
        AccountLogDO log = new AccountLogDO();
        log.setUuid(context.getUuid());
        log.setProId(context.getProId());
        log.setEnv(context.getEnv());
        log.setBizType(1);
        log.setRequestIp(context.getRequestIp());
        log.setTenantId(context.getUserObject().getTenantEnum().getTenantId());
        return log;
    }
}
