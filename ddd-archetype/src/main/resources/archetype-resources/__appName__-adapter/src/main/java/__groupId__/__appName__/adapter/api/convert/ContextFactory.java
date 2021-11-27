#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.convert;

import com.yxkong.common.entity.common.LoginToken;
import ${package}.domain.dto.context.DistributeContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 上下文工厂
 *
 * @Author: ${author}
 * @Date: 2021/6/3 6:51 下午
 * @version: ${version}
 */
@Slf4j
public class ContextFactory {
    /**
     * 获取分发上下文对象
     * @param loginToken 用户登录token
     * @return
     */
    public static DistributeContext distributeContext(LoginToken loginToken){
        return new DistributeContext(loginToken);
    }
}