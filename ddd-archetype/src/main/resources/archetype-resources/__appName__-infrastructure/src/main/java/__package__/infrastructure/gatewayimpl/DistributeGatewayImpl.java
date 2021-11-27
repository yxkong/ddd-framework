#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.gatewayimpl;

import com.${author}.${appName}.domain.dto.resp.DistributeDTO;
import com.${author}.${appName}.domain.gateway.DistributeGateway;
import com.${author}.${appName}.domain.model.user.CustomerId;
import org.springframework.stereotype.Service;

/**
 * 分发结果网关实现
 * @Author: ${author}
 * @Date: 2021/6/3 8:23 下午
 * @version: ${version}
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