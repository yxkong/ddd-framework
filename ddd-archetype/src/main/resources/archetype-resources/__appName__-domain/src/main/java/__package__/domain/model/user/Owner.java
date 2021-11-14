#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import ${groupId}.common.annotation.DomainValueObject;
import lombok.Builder;
import lombok.Getter;

/**
 * @Author: ${author}
 * @date 2021-06-10 17:17
 */
@Builder
@Getter
@DomainValueObject
public class Owner {
    private final CustomerId customerId;
    /**
     * 用户身份证号（严格意义上此为业务唯一主键，但在我们系统，我们认为id才是唯一）
     */
    private final String certId;
    /**
     * 用户手机号码
     */
    private final String mobile;
    /**
     * 用户姓名
     */
    private final String realName;
    /**
     * 用户来源
     */
    private final Integer source;
    /**
     * 用户注册渠道
     */
    private final String proId;
}
