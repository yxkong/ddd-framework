#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.sms;

import ${groupId}.common.annotation.DomainEntity;
import ${groupId}.common.constant.TenantEnum;
import lombok.Getter;

/**
 * 日志实体
 * @Author: ${author}
 * @Date: 2021/11/24 10:54 AM
 * @version: ${version}
 */
@DomainEntity
@Getter
public class SmsLogId {
    private Long id;
    private TenantEnum tenant;
    public SmsLogId(Long id,TenantEnum tenant) {
        this.id = id;
        this.tenant = tenant;
    }
}
