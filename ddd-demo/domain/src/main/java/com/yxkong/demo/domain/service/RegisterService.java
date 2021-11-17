package com.yxkong.demo.domain.service;

import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.util.ResultBeanUtil;
import com.yxkong.demo.domain.constant.DomainResultEnum;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.gateway.RegisterGateway;
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
public class RegisterService {



    RegisterGateway registerGateway;
    /**
     * 注册服务
     * @param context
     * @return
     */
    public ResultBean register(RegisterContext context){
        boolean exist = registerGateway.isExist(context.getUserObject());
        if (exist){
            return ResultBeanUtil.result(DomainResultEnum.REGISTERED);
        }
        AccountEntity accountEntity = registerGateway.register(context);
        if (Objects.nonNull(accountEntity)){
            return ResultBeanUtil.success("注册成功！",accountEntity);
        }
        return ResultBeanUtil.fail("注册失败！",null);
    }
}
