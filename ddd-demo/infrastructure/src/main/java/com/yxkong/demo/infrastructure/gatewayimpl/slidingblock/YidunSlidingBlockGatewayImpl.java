package com.yxkong.demo.infrastructure.gatewayimpl.slidingblock;

import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.dto.resp.SlidingBlockDto;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import org.springframework.stereotype.Service;

/**
 * 易盾滑块验证实现
 *
 * @Author: yxkong
 * @Date: 2021/11/15 12:24 PM
 * @version: 1.0
 */
@Service("yidunSlidingBlockGateway")
public class YidunSlidingBlockGatewayImpl implements SlidingBlockSupplierGateway {
    @Override
    public SlidingBlockDto check(SlidingBlockContext context) {
        return null;
    }
}
