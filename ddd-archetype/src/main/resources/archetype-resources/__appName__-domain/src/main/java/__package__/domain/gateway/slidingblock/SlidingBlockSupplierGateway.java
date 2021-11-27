#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.gateway.slidingblock;

import ${package}.domain.dto.context.SlidingBlockContext;
import ${package}.domain.dto.resp.SlidingBlockDto;
/**
 * 滑块验证供应商网关（内部统一封装）
 *
 * @Author: ${author}
 * @Date: 2021/11/15 11:20 AM
 * @version: ${version}
 */
public interface SlidingBlockSupplierGateway {
    /**
     * 滑块验证
     * @param context  滑块验证上下文
     * @return
     */
    SlidingBlockDto check(SlidingBlockContext context);

}
