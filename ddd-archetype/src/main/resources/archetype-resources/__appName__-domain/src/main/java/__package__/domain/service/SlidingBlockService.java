#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.service;

import ${groupId}.common.annotation.DomainService;
import ${groupId}.common.entity.dto.ResultBean;
import ${groupId}.common.util.ResultBeanUtil;
import ${package}.domain.dto.context.SlidingBlockContext;
import ${package}.domain.dto.resp.SlidingBlockDto;
import ${package}.domain.gateway.slidingblock.SlidingBlockGateway;
import ${package}.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import lombok.Builder;

/**
 * 滑块领域服务
 * @Author: ${author}
 * @Date: 2021/11/15 1:39 PM
 * @version: ${version}
 */
@DomainService
@Builder
public class SlidingBlockService {

    SlidingBlockGateway slidingBlockGateway;
    SlidingBlockSupplierGateway slidingBlockSupplierGateway;

    /**
     * 滑块验证服务
     * @param context
     * @return
     */
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
            return ResultBeanUtil.success(blockDto.getMessage(),null);
        }
        return ResultBeanUtil.fail(blockDto.getMessage(),null);
    }
}
