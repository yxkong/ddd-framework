package com.yxkong.demo.infrastructure.gatewayimpl.rpc;


import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.gateway.QuotaGateway;
import com.yxkong.demo.domain.model.quota.QuotaEntity;
import com.yxkong.demo.domain.model.user.CustomerId;
import com.yxkong.demo.infrastructure.convert.QuotaAppStatusConvert;
import com.yxkong.demo.infrastructure.convert.QuotaFreezeConvert;
import com.yxkong.demo.infrastructure.remote.QuotaFeignService;
import com.yxkong.demo.infrastructure.remote.dto.QuotaSingleDto;
import com.yxkong.demo.infrastructure.remote.vo.QuotaSingleQueryVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public QuotaEntity queryQuota(CustomerId customerId) {
        ResultBean<QuotaSingleDto> resultBean = quotaFeignService.findSingleQuotaByCustomerId(new QuotaSingleQueryVo(customerId.getId()));
        if (!resultBean.isSucc() || Objects.isNull(resultBean.getData())) {
            return null;
        }
        QuotaSingleDto data = resultBean.getData();
        QuotaEntity quotaEntity = QuotaEntity.builder().customerId(customerId)
                .quotaAppStatus(QuotaAppStatusConvert.reverseMapping(data.getAppStatus()))
                .quotaFreezeStatus(QuotaFreezeConvert.reverseMapping(data.getFreezeStatus())).build();
        BeanUtils.copyProperties(data, quotaEntity);
        return quotaEntity;
    }


}
