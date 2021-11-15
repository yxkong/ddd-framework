package com.yxkong.demo.domain.service;

import com.yxkong.common.annotation.DomainService;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.common.util.ResultBeanUtil;
import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.dto.resp.SlidingBlockDto;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockGateway;
import com.yxkong.demo.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import lombok.Builder;

/**
 * 滑块领域服务
 * @Author: yxkong
 * @Date: 2021/11/15 1:39 PM
 * @version: 1.0
 */
@DomainService
@Builder
public class SlidingBlockService {

    SlidingBlockGateway slidingBlockGateway;
    SlidingBlockSupplierGateway slidingBlockSupplierGateway;
    public ResultBean check(SlidingBlockContext context){
        /**
         * 校验滑块id是否使用（校验成功一次后就失效）
         */
        boolean  used =  slidingBlockGateway.validate(context.getSlidingBlock());
        if (used){
            return ResultBeanUtil.fail("请务重复提交",null);
        }
        /**
         * 滑块id，使用失败的多少次
         */
        int count = slidingBlockGateway.failCount(context.getSlidingBlock());
        if (count > 3){
            return ResultBeanUtil.fail("请您重新验证后提交",null);
        }

        SlidingBlockDto blockDto = slidingBlockSupplierGateway.check(context);
        if (blockDto.getResult()){
            return ResultBeanUtil.success();
        }
        return ResultBeanUtil.fail(blockDto.getMessage(),null);
    }
}
