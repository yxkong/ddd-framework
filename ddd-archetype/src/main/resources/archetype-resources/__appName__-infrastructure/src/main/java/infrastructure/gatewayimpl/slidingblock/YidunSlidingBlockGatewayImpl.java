#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.gatewayimpl.slidingblock;

import ${package}.domain.dto.context.SlidingBlockContext;
import ${package}.domain.dto.resp.SlidingBlockDto;
import ${package}.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import org.springframework.stereotype.Service;

/**
 * 易盾滑块验证实现
 *
 * @Author: ${author}
 * @Date: 2021/11/15 12:24 PM
 * @version: ${version}
 */
@Service("yidunSlidingBlockGateway")
public class YidunSlidingBlockGatewayImpl implements SlidingBlockSupplierGateway {
    @Override
    public SlidingBlockDto check(SlidingBlockContext context) {
        return SlidingBlockDto.builder().result(true).message("易盾校验成功！").build();
    }
}
