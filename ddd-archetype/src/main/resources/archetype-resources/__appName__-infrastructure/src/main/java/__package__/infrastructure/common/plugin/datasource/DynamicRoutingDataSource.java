#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.datasource;

import ${package}.infrastructure.common.constant.DataSourceKey;
import ${groupId}.common.constant.TenantEnum;
import ${package}.infrastructure.common.util.LoginTokenUtil;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.StringUtils;

/**
 * 动态数据源
 *
 * @Author: ${author}
 * @Date: 2021/6/2 6:02 下午
 * @version: ${version}
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //获取租户号。得到KEY。
        String tenantId = String.valueOf(LoginTokenUtil.getTenantId());
        if (StringUtils.isEmpty(tenantId) || tenantId.equalsIgnoreCase(TenantEnum.main.getTenantId()+"")) {
            return DataSourceKey.main.name();
        } else {
            return DataSourceKey.other.name();
        }
    }
}