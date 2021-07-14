package com.yxkong.demo.infrastructure.common.plugin.datasource;

import com.yxkong.common.constant.TenantEnum;
import com.yxkong.demo.infrastructure.common.configuration.druid.DataSourceKey;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

/**
 * 动态数据源
 *
 * @Author: yxkong
 * @Date: 2021/6/2 6:02 下午
 * @version: 1.0
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        //获取租户号。得到KEY。
        String tenantId = String.valueOf(LoginTokenUtil.getTenantId());
        if (StringUtils.isEmpty(tenantId) || tenantId.equalsIgnoreCase(TenantEnum.main.getTenantId()+"")) {
            return DataSourceKey.main.name();
        } else {
            return DataSourceKey.x.name();
        }
    }
}