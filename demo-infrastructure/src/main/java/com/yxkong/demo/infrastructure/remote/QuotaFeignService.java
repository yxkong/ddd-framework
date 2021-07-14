package com.yxkong.demo.infrastructure.remote;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.infrastructure.remote.dto.QuotaSingleDto;
import com.yxkong.demo.infrastructure.remote.vo.QuotaSingleQueryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: yxkong
 * @Date: 2021/7/14 4:01 下午
 * @version: 1.0
 */
@FeignClient("${eureka.instance.service.quotaService}")
public interface QuotaFeignService {

    /**
     * 根据customerId查询用户额度激活状态
     *
     * @param quotaSingleQueryVo
     * @return
     */
    @RequestMapping(value = "/api/quota/findSingleQuotaByCustomerId", method = {RequestMethod.POST})
    ResultBean<QuotaSingleDto> findSingleQuotaByCustomerId(@RequestBody QuotaSingleQueryVo quotaSingleQueryVo);


}