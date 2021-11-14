package com.yxkong.demo.infrastructure.rpc.internal;
import com.yxkong.demo.infrastructure.rpc.internal.vo.ChangeQuotaVo;
import com.yxkong.demo.infrastructure.rpc.internal.vo.QuotaSingleQueryVo;
import com.yxkong.demo.infrastructure.rpc.internal.vo.QuotaSingleVo;
import com.yxkong.demo.infrastructure.rpc.internal.vo.UpdateDiffQuotaRequestVo;
import com.yxkong.common.entity.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("${eureka.instance.service.quotaService}")
public interface QuotaFeignService {

    /**
     * 根据customerId查询用户额度激活状态
     *
     * @param quotaSingleQueryVo
     * @return
     */
    @RequestMapping(value = "/api/quota/findSingleQuotaByCustomerId", method = {RequestMethod.POST})
    ResultBean<QuotaSingleVo> findSingleQuotaByCustomerId(@RequestBody QuotaSingleQueryVo quotaSingleQueryVo);

    /**
     * 提额
     * @param vo
     * @return
     */
    @RequestMapping(value = "/api/quota/ext/change", method = {RequestMethod.POST})
    ResultBean<ChangeQuotaVo> quotaChange(@SpringQueryMap UpdateDiffQuotaRequestVo vo);
}
