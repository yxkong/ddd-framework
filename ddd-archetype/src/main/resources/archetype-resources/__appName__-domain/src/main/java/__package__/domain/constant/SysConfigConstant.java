#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.constant;

/**
 * 配置表常量
 * 按理说不应该放这，为了通用放这里了
 * @Author: ${author}
 * @Date: 2021/6/8 2:15 下午
 * @version: ${version}
 */
public class SysConfigConstant {

    public static final String WK_SYSTEM = "wk";

    /**
     * token配置表key
     */
    public static final String AUTHORIZATION_KEY = "authapi_paymentservice_authorization";

    public static final String AUTH_TASK_OVERTIME = "auth_task_overtime";

    public static final String AUTH_DISTRIBUTE_SWITCH_KEY = "auth_distribute_switch";
    public static final String DEFAULT_CLOSE = "close";
    public static final String DEFAULT_OPEN = "open";
    
    public static final String AUTH_DISTRIBUTE_MOBILE = "auth_distribute_mobile";
    public static final String AUTH_DISTRIBUTE_MOBILE_NUM = "auth_distribute_mobile_num";
}
