#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * @Author: ${author}
 * @Date: 2021/6/3 7:10 下午
 * @version: ${version}
 */
package ${package}.domain.gateway;

import ${package}.domain.model.quota.QuotaEntity;
import ${package}.domain.model.user.CustomerId;

import java.math.BigDecimal;

/**
 * 〈额度网关〉
 *
 * @Author: ${author}
 * @create 2021/6/3
 * @since ${version}.0
 */
public interface QuotaGateway {
    /**
     * 获取额度领域对象
     *
     * @param customerId
     * @return
     */
    QuotaEntity queryQuota(CustomerId customerId);

    /**
     * 查询信审服务获取额度状态
     *
     * @param customerId
     * @return
     */
    QuotaEntity queryCheck(CustomerId customerId);

    // QuotaEntity freezeOrNot(CustomerId customerId);

    /**
     * 提额
     *
     * @param customerId
     * @param amt
     * @param isPerm
     * @param reason
     * @return
     */
    boolean incrQuota(CustomerId customerId, BigDecimal amt, Integer isPerm, String reason);
}
