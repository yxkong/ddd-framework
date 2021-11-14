package com.yxkong.demo.infrastructure.rpc.internal;

import com.yxkong.demo.infrastructure.rpc.internal.vo.HasFaceResponseVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yxkong.common.entity.dto.ResultBean;

/**
 * @author 
 * @date 2021/6/15 19:36
 */
@FeignClient(value = "${eureka.instance.service.faceService}")
public interface FaceFeignService {

    @PostMapping(value = "/faceApi/faceQuery/hasFaceForServe")
    ResultBean<HasFaceResponseVo> hasFaceForServe(@RequestParam(name = "certId") String certId, @RequestParam(name = "customerId") Long customerId);

}
