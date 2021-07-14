package com.yxkong.demo.executor;


import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.domain.gateway.AuthTaskGateway;
import com.yxkong.demo.domain.gateway.ConfigGateway;
import com.yxkong.demo.domain.gateway.DistributeGateway;
import com.yxkong.demo.domain.gateway.QuotaGateway;
import com.yxkong.demo.domain.service.DistributeService;
import com.yxkong.demo.infrastructure.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 授信分发应用
 *
 * @Author: yxkong
 * @Date: 2021/6/3 5:49 下午
 * @version: 1.0
 */
@Service
public class AuthDistributeExecutor {

    @Resource
    private QuotaGateway quotaGateway;
    @Resource
    private AuthTaskGateway authTaskGateway;
    @Resource
    private ConfigGateway configGateway;
    @Resource
    private DistributeGateway distributeGateway;

    /**
     * 应用层执行
     *
     * @param context
     * @return
     */
    public ResultBean<DistributeDTO> executor(DistributeContext context) {
        /**
         * 构建领域服务
         */
        DistributeService service = DistributeService.builder()
                .authTaskGateway(authTaskGateway)
                .quotaGateway(quotaGateway)
                .configGateway(configGateway)
                .build();
        /**
         * 领域服务执行
         */
        ResultBean<DistributeDTO> resultBean = service.distribute(context);
        resultBean.getData().setAuthUuid(StringUtils.randomUUIDSplit());

        /** 这下面的代码是非业务性功能，需要异步执行**/
        distributeGateway.doResult(resultBean.getData(), context.getCustomerId());
        return resultBean;

    }

}