#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.gatewayimpl.database;

import ${package}.domain.gateway.ConfigGateway;
import ${package}.infrastructure.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 配置项查询
 *
 * @Author: ${author}
 * @Date: 2021/6/3 8:22 下午
 * @version: ${version}
 */
@Service
public class ConfigGatewayImpl implements ConfigGateway {

    @Resource
    private SysConfigService sysConfigService;

    @Override
    public String query(String key, String defaultValue) {
        String val = sysConfigService.findValue(key, "module");
        if (Objects.nonNull(val)){
            return val;
        }
        return defaultValue;
    }
}
