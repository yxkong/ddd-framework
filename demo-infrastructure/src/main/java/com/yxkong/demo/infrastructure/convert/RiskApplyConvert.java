package com.yxkong.demo.infrastructure.convert;

import com.yxkong.demo.domain.model.authtask.AuthTask;
import com.yxkong.demo.domain.model.authtask.AuthTaskId;
import com.yxkong.demo.domain.model.user.CustomerId;
import com.yxkong.demo.infrastructure.persistence.entity.loanandx.RiskApplyDO;
import org.springframework.beans.BeanUtils;

/**
 * @Author: yxkong
 * @Date: 2021/7/14 5:49 下午
 * @version: 1.0
 */
public class RiskApplyConvert {

    public static AuthTask convert(RiskApplyDO riskApplyDO) {
        if (riskApplyDO == null) {
            return null;
        }
        AuthTask.AuthTaskBuilder authTaskBuilder = AuthTask.builder().taskId(new AuthTaskId(riskApplyDO.getId()));

        authTaskBuilder.customerId(new CustomerId(riskApplyDO.getCustomerId(), riskApplyDO.getTenantId()));
        AuthTask authTask = authTaskBuilder.build();
        BeanUtils.copyProperties(riskApplyDO, authTask);
        return authTask;
    }
}