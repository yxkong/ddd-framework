package com.yxkong.demo.infrastructure.gatewayimpl.rpc;

import com.yxkong.demo.domain.model.user.CustomerId;
import com.yxkong.demo.infrastructure.rpc.internal.vo.*;
import com.yxkong.demo.domain.gateway.QuotaGateway;
import com.yxkong.demo.domain.model.quota.QuotaEntity;
import com.yxkong.demo.infrastructure.convert.QuotaAppStatusMapping;
import com.yxkong.demo.infrastructure.convert.QuotaFreezeMapping;
import com.yxkong.demo.infrastructure.rpc.internal.QuotaCheckFeignService;
import com.yxkong.demo.infrastructure.rpc.internal.QuotaFeignService;
import com.yxkong.common.entity.dto.ResultBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 额度网关实现
 *
 * @Author: yxkong
 * @Date: 2021/6/3 8:15 下午
 * @version: 1.0
 */
@Service
public class QuotaGatewayImpl implements QuotaGateway {

    @Autowired
    private QuotaFeignService quotaFeignService;
    @Autowired
    private QuotaCheckFeignService quotaCheckFeignService;

    /**
     * 引入额度查询的feign
     */
    @Override
    public QuotaEntity queryQuota(CustomerId customerId) {
        ResultBean<QuotaSingleVo> resultBean = quotaFeignService.findSingleQuotaByCustomerId(new QuotaSingleQueryVo(customerId.getId()));
        if (!resultBean.isSucc() || Objects.isNull(resultBean.getData())) {
            return null;
        }
        QuotaSingleVo data = resultBean.getData();
        QuotaEntity quotaEntity = QuotaEntity.builder().customerId(customerId).quotaAppStatus(QuotaAppStatusMapping.reverseMapping(data.getAppStatus()))
                .quotaFreezeStatus(QuotaFreezeMapping.reverseMapping(data.getFreezeStatus())).build();
        BeanUtils.copyProperties(data, quotaEntity);
        return quotaEntity;
    }

    @Override
    public QuotaEntity queryCheck(CustomerId customerId) {
        ResultBean<QuotaCheckVo> resultBean = quotaCheckFeignService.findStatusByCustomerId(customerId.getId());
        if (!resultBean.isSucc() || Objects.isNull(resultBean.getData())) {
            return null;
        }
        QuotaCheckVo data = resultBean.getData();
        QuotaEntity quotaEntity = QuotaEntity.builder().customerId(customerId).quotaAppStatus(QuotaAppStatusMapping.reverseMapping(data.getAppStatus())).build();
        return quotaEntity;
    }

    @Override
    public boolean incrQuota(CustomerId customerId, BigDecimal amt, Integer isPerm, String reason) {
        UpdateDiffQuotaRequestVo updateDiffQuotaRequestVo = UpdateDiffQuotaRequestVo.builder().customerId(customerId.getId())
                .amt(amt).isPerm(isPerm).reason(reason).build();
        ResultBean<ChangeQuotaVo> resultBean = quotaFeignService.quotaChange(updateDiffQuotaRequestVo);
        if (!resultBean.isSucc() || Objects.isNull(resultBean.getData())) {
            return false;
        }
        return true;
    }
}
