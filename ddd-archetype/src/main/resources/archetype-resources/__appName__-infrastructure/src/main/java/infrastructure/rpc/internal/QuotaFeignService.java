#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal;
import ${package}.infrastructure.rpc.internal.vo.ChangeQuotaVo;
import ${package}.infrastructure.rpc.internal.vo.QuotaSingleQueryVo;
import ${package}.infrastructure.rpc.internal.vo.QuotaSingleVo;
import ${package}.infrastructure.rpc.internal.vo.UpdateDiffQuotaRequestVo;
import com.yxkong.common.entity.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@FeignClient("${symbol_dollar}{eureka.instance.service.quotaService}")
@FeignClient("quotaService")
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
