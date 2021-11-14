#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal;

import ${package}.infrastructure.rpc.internal.vo.QuotaCheckVo;
import ${groupId}.common.entity.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: ${author}
 * @date 2021/6/16-17:31
 */
@FeignClient(value = "${symbol_dollar}{eureka.instance.service.quotaCheckService}")
public interface QuotaCheckFeignService {

    @RequestMapping(value = "api/wkcheck/quota/findStatusByCustomerId", method = {RequestMethod.POST})
    ResultBean<QuotaCheckVo> findStatusByCustomerId(@RequestParam(value = "customerId") Long customerId);

    /**
     * 是否是学生
     */
    @RequestMapping(value = "api/wkcheck/refuse/label/studentOrNot", method = {RequestMethod.POST})
    ResultBean<Boolean> studentOrNot(@RequestParam(value = "certId") String certId, @RequestParam(value = "customerId") Long customerId);
}
