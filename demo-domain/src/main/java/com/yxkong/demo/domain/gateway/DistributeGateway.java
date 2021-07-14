/**
 * 分发网关
 *
 * @Author: yxkong
 * @Date: 2021/6/3 8:07 下午
 * @version: 1.0
 */
package com.yxkong.demo.domain.gateway;


import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.domain.model.user.CustomerId;

/**
 * 〈分发网关〉
 *
 * @author yxkong
 * @create 2021/6/3
 * @since 1.0.0
 */
public interface DistributeGateway {
    /**
     * 处理分发结果(非业务功能性需求)
     * @param distributeDTO
     * @param customerId
     */
    void doResult (DistributeDTO distributeDTO, CustomerId customerId);
}
