package com.yxkong.demo.infrastructure.gatewayimpl.slidingblock;

import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockGateway;
import com.yxkong.demo.domain.model.slidingblock.SlidingBlock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 滑块共用操作网关
 *
 * @Author: yxkong
 * @Date: 2021/11/23 10:54 AM
 * @version: 1.0
 */
@Service
@Slf4j
public class SlidingBlockGatewayImpl implements SlidingBlockGateway {
    @Override
    public boolean validate(SlidingBlock slidingBlock) {
        log.info("本地校验{}滑块id：{}使用通过，减少外部调用",slidingBlock.getSlidingBlockSupplier(),slidingBlock.getSlidingBlockId());
        return false;
    }

    @Override
    public int failCount(SlidingBlock slidingBlock) {
        log.info("本地记录{}滑块id:{}失败次数为0",slidingBlock.getSlidingBlockSupplier(),slidingBlock.getSlidingBlockId());
        return 0;
    }

    @Override
    public void updateChecked(SlidingBlockContext context) {
        log.info("更新{}滑块id:{}为已使用",context.getSlidingBlock().getSlidingBlockSupplier(),context.getSlidingBlock().getSlidingBlockId());
    }

    @Override
    public void updateFailCount(SlidingBlockContext context) {
        log.info("本地记录{}滑块id:{}失败次数",context.getSlidingBlock().getSlidingBlockSupplier(),context.getSlidingBlock().getSlidingBlockId());
    }
}
