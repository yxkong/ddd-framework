package com.yxkong.demo.infrastructure.rpc.internal;

import com.yxkong.common.entity.dto.ResultBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 
 * @date 2021/6/15 19:36
 */
@FeignClient(value = "${eureka.instance.service.userinfoApi}")
public interface UserInfoFeignService {

    @PostMapping(value = "/route/gather/confCheck")
    ResultBean confCheck(@RequestParam(name = "customerId") Long customerId, @RequestParam(name = "module") String module);

}
