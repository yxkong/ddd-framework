#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal;

import com.yxkong.common.entity.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ${author}
 * @date 2021/6/15 19:36
 */
//@FeignClient(value = "${symbol_dollar}{eureka.instance.service.userinfoApi}")
@FeignClient(value = "userinfoApi")
public interface UserInfoFeignService {

    @PostMapping(value = "/route/gather/confCheck")
    ResultBean confCheck(@RequestParam(name = "customerId") Long customerId, @RequestParam(name = "module") String module);

}
