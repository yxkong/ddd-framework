package com.yxkong.demo.application.executor;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.domain.gateway.ConfigGateway;
import com.yxkong.demo.domain.gateway.DistributeGateway;
import com.yxkong.demo.domain.gateway.QuotaGateway;
import com.yxkong.demo.domain.service.AuthDistributeService;
import com.yxkong.demo.infrastructure.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 授信分发应用
 *
 * @Author: ${author}
 * @Date: 2021/6/3 5:49 下午
 * @version: 1.0
 */
@Service
public class AuthDistributeExecutor {

    @Resource
    private QuotaGateway quotaGateway;
    @Resource
    private ConfigGateway configGateway;
    @Resource
    private DistributeGateway distributeGateway;

    /**
     * 应用层执行
     *
     * @param context 应用上下文（如果只是单个领域，这个上下文可以是领域上下文）
     * @return
     */
//    @Transactional 如果没有外部调用，有事务，可以在这里加事务，如果有外部调用，在基础设施层加
    public ResultBean<DistributeDTO> executor(DistributeContext context) {
        /**
         * 构建领域服务
         *   将应用上下文按领域拆解
         *   如果多个领域，拆开执行
         */
        AuthDistributeService service = AuthDistributeService.builder()
                .quotaGateway(quotaGateway)
                .configGateway(configGateway)
                .build();
        /**
         * 领域执行
         */
        ResultBean<DistributeDTO> resultBean = service.distribute(context);
        resultBean.getData().setAuthUuid(StringUtils.randomUUIDRmLine());

        /** 这下面的代码是非业务性功能，需要异步执行**/
        distributeGateway.doResult(resultBean.getData(), context.getCustomerId());
        return resultBean;

    }

}