/**
 * @Author: yxkong
 * @Date: 2021/6/3 7:10 下午
 * @version: 1.0
 */
package com.yxkong.demo.domain.gateway;


import com.yxkong.demo.domain.model.quota.QuotaEntity;
import com.yxkong.demo.domain.model.user.CustomerId;

import java.math.BigDecimal;

/**
 * 〈额度网关〉
 *
 * @author yxkong
 * @create 2021/6/3
 * @since 1.0.0
 */
public interface QuotaGateway {
    /**
     * 获取额度领域对象
     *
     * @param customerId
     * @return
     */
    QuotaEntity queryQuota(CustomerId customerId);

}
