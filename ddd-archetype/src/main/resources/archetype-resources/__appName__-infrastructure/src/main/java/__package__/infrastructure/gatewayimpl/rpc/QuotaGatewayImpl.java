#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.gatewayimpl.rpc;

import com.${author}.${appName}.domain.model.user.CustomerId;
import ${package}.infrastructure.rpc.internal.vo.*;
import com.${author}.${appName}.domain.gateway.QuotaGateway;
import com.${author}.${appName}.domain.model.quota.QuotaEntity;
import ${package}.infrastructure.convert.QuotaAppStatusMapping;
import ${package}.infrastructure.convert.QuotaFreezeMapping;
import ${package}.infrastructure.rpc.internal.QuotaCheckFeignService;
import ${package}.infrastructure.rpc.internal.QuotaFeignService;
import ${groupId}.common.entity.dto.ResultBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * 额度网关实现
 *
 * @Author: ${author}
 * @Date: 2021/6/3 8:15 下午
 * @version: ${version}
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
