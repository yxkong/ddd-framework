#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * 分发网关
 *
 * @Author: ${author}
 * @Date: 2021/6/3 8:07 下午
 * @version: ${version}
 */
package ${package}.domain.gateway;

import ${package}.domain.dto.resp.DistributeDTO;
import ${package}.domain.model.user.CustomerId;

/**
 * 〈分发网关〉
 *
 * @Author: ${author}
 * @create 2021/6/3
 * @since ${version}.0
 */
public interface DistributeGateway {
    /**
     * 处理分发结果(非业务功能性需求)
     * @param distributeDTO
     * @param customerId
     */
    void doResult (DistributeDTO distributeDTO, CustomerId customerId);
}
