package com.yxkong.demo.domain.gateway.slidingblock;

import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.model.slidingblock.SlidingBlock;

/**
 * 滑块公共操作
 *
 * @Author: yxkong
 * @Date: 2021/11/15 2:30 PM
 * @version: 1.0
 */
public interface SlidingBlockGateway {
    /**
     * 验证滑块id是否已使用，减少外部的调用
     * @param slidingBlock
     * @return
     */
    boolean validate(SlidingBlock slidingBlock);

    /**
     * 验证失败的次数
     * @param slidingBlock
     * @return
     */
    int failCount(SlidingBlock slidingBlock);

    /**
     * 更新滑块为已使用
     * @param context
     */
    void updateChecked(SlidingBlockContext context);

    /**
     * 更新滑块失败次数
     * @param context
     */
    void updateFailCount(SlidingBlockContext context);
}
