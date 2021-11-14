package com.yxkong.demo.infrastructure.gatewayimpl;

import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.domain.gateway.DistributeGateway;
import com.yxkong.demo.domain.model.user.CustomerId;
import org.springframework.stereotype.Service;

/**
 * 分发结果网关实现
 * @Author: yxkong
 * @Date: 2021/6/3 8:23 下午
 * @version: 1.0
 */
@Service
public class DistributeGatewayImpl implements DistributeGateway {
    @Override
    public void doResult(DistributeDTO distributeDTO, CustomerId customerId) {
        /**
         * 1，异步处理
         * 2，先将结果发出去，然后再保存到数据库
         */

    }
}