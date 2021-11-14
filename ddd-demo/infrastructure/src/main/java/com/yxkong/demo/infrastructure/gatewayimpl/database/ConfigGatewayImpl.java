package com.yxkong.demo.infrastructure.gatewayimpl.database;

import com.yxkong.demo.domain.constant.SysConfigConstant;
import com.yxkong.demo.domain.gateway.ConfigGateway;
import com.yxkong.demo.infrastructure.service.SysConfigService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 配置项查询
 *
 * @Author: yxkong
 * @Date: 2021/6/3 8:22 下午
 * @version: 1.0
 */
@Service
public class ConfigGatewayImpl implements ConfigGateway {

    @Resource
    private SysConfigService sysConfigService;

    @Override
    public String query(String key, String defaultValue) {
        String val = sysConfigService.findValue(key, SysConfigConstant.WK_SYSTEM);
        if (Objects.nonNull(val)){
            return val;
        }
        return defaultValue;
    }
}
