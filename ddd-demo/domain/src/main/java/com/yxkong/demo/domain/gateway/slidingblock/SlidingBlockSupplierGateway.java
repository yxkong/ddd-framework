package com.yxkong.demo.domain.gateway.slidingblock;

import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.dto.resp.SlidingBlockDto;
/**
 * 滑块验证供应商网关（内部统一封装）
 *
 * @Author: yxkong
 * @Date: 2021/11/15 11:20 AM
 * @version: 1.0
 */
public interface SlidingBlockSupplierGateway {
    /**
     * 滑块验证
     * @param context  滑块验证上下文
     * @return
     */
    SlidingBlockDto check(SlidingBlockContext context);

}
