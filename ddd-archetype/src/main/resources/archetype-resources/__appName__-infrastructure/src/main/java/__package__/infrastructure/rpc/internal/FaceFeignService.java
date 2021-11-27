#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal;

import ${package}.infrastructure.rpc.internal.vo.HasFaceResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ${groupId}.common.entity.dto.ResultBean;

/**
 * @author ${author}
 * @date 2021/6/15 19:36
 */
//@FeignClient(value = "${symbol_dollar}{eureka.instance.service.faceService}")
@FeignClient(value = "faceService")
public interface FaceFeignService {

    @PostMapping(value = "/faceApi/faceQuery/hasFaceForServe")
    ResultBean<HasFaceResponseVo> hasFaceForServe(@RequestParam(name = "certId") String certId, @RequestParam(name = "customerId") Long customerId);

}
