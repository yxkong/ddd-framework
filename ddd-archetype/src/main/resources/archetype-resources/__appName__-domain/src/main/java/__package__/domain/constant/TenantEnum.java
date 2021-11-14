#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.constant;

import lombok.Getter;

/**
 * 租户枚举
 *
 * @Author: ${author}
 * @Date: 2021/6/2 6:08 下午
 * @version: ${version}
 */
@Getter
public enum TenantEnum {

    ONE_CARD(1001, "万卡"),
    FISH_CARD(2010, "小鱼福卡");

    private final Integer tenantId;
    private final String name;

    TenantEnum(Integer tenantId, String name) {
        this.tenantId = tenantId;
        this.name = name;
    }

    public static TenantEnum getDefault() {
        return TenantEnum.ONE_CARD;
    }
}
