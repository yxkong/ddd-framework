#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.executor;

import ${groupId}.common.entity.dto.ResultBean;
import ${package}.domain.dto.context.DistributeContext;
import ${package}.domain.dto.resp.DistributeDTO;
import ${package}.domain.gateway.ConfigGateway;
import ${package}.domain.gateway.DistributeGateway;
import ${package}.domain.gateway.QuotaGateway;
import ${package}.domain.service.AuthDistributeService;
import ${package}.infrastructure.common.util.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 授信分发应用
 *
 * @Author: ${symbol_dollar}{author}
 * @Date: 2021/6/3 5:49 下午
 * @version: ${version}
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