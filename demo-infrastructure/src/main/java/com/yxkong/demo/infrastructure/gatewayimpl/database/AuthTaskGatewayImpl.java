package com.yxkong.demo.infrastructure.gatewayimpl.database;

import com.yxkong.demo.domain.gateway.AuthTaskGateway;
import com.yxkong.demo.domain.model.authtask.AuthTask;
import com.yxkong.demo.domain.model.user.CustomerId;
import com.yxkong.demo.infrastructure.convert.RiskApplyConvert;
import com.yxkong.demo.infrastructure.persistence.entity.loanandx.RiskApplyDO;
import com.yxkong.demo.infrastructure.persistence.mapper.loanandx.RiskApplyMapper;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Author: yxkong
 * @Date: 2021/7/14 5:43 下午
 * @version: 1.0
 */
public class AuthTaskGatewayImpl implements AuthTaskGateway {
    @Autowired
    private RiskApplyMapper riskApplyMapper;
    @Override
    public AuthTask queryLatest(CustomerId customerId) {
        RiskApplyDO riskApplyDO = riskApplyMapper.selectLatest(customerId.getId());
        return RiskApplyConvert.convert(riskApplyDO);
    }
}