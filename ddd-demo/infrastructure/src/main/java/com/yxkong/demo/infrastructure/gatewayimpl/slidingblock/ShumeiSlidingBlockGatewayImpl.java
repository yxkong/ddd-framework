package com.yxkong.demo.infrastructure.gatewayimpl.slidingblock;

import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.dto.resp.SlidingBlockDto;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import org.springframework.stereotype.Service;

/**
 * 数美
 *
 * @Author: yxkong
 * @Date: 2021/11/15 2:48 PM
 * @version: 1.0
 */
@Service("shumeiSlidingBlockGateway")
public class ShumeiSlidingBlockGatewayImpl implements SlidingBlockSupplierGateway {
    @Override
    public SlidingBlockDto check(SlidingBlockContext context) {
        return SlidingBlockDto.builder().result(true).message("数美校验成功！").build();
    }
}
