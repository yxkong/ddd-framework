#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import ${groupId}.common.annotation.AggregateRoot;
import lombok.Builder;
import lombok.Getter;

/**
 * 账户领域对象
 *
 * @Author: ${author}
 * @Date: 2021/5/31 6:13 下午
 * @version: ${version}
 */
@Getter
@Builder
@AggregateRoot
public class AccountEntity {
    private AccountId accountId;
    /**
     * 用户手机号码
     */
    private String mobile;
    /**
     * 用户中心的uuid
     */
    private String uuid;
    /**
     * 用户中心的客户id
     */
    private String custNo;
    /**
     * 用户注册渠道
     */
    private String proId;
}
