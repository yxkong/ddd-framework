#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.gatewayimpl.slidingblock;

import ${package}.domain.dto.context.SlidingBlockContext;
import ${package}.domain.dto.resp.SlidingBlockDto;
import ${package}.domain.gateway.slidingblock.SlidingBlockSupplierGateway;
import org.springframework.stereotype.Service;

/**
 * 数美
 *
 * @Author: ${author}
 * @Date: 2021/11/15 2:48 PM
 * @version: ${version}
 */
@Service("shumeiSlidingBlockGateway")
public class ShumeiSlidingBlockGatewayImpl implements SlidingBlockSupplierGateway {
    @Override
    public SlidingBlockDto check(SlidingBlockContext context) {
        return SlidingBlockDto.builder().result(true).message("数美校验成功！").build();
    }
}
