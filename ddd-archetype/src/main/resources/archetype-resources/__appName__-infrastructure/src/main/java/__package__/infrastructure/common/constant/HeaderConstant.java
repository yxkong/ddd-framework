#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.constant;

/**
 * 请求头常量
 *
 * @Author: ${author}
 * @Date: 2021/5/12 2:31 下午
 * @version: ${version}
 */
public class HeaderConstant {
    public static final String TOKEN = "token";
    public static final String PRO_ID = "proId";
    public static final String TENANT_ID = "tenantId";
    public static final String LOGIN_INFO = "loginInfo";
    public static final String REQUEST_FROM = "requestFrom";
    public static final String REQUEST_FROM_SOURCE = "gateway";
    public static final String IP_UNKNOWN = "unknown";
    public static final String IP_HEADER_REQUEST_SOURCE_IP = "requestSourceIp";
    public static final String IP_HEADER_X_REQUESTED_FOR = "X-requested-For";
    public static final String IP_HEADER_X_FORWARDED_FOR = "x-forwarded-for";
    public static final String IP_HEADER_HTTP_X_FORWARDED_FOR = "http_x-forwarded-for";
    public static final String IP_HEADER_WL_PROXY_CLIENT_IP = "wl-proxy-client-iP";
    public static final String IP_HEADER_PROXY_CLIENT_IP = "proxy-client-ip";
    public static final String IP_HEADER_HTTP_CLIENT_IP = "http_client_ip";
    public static final String X_APPUUID = "x-appUuid";
    public static final String X_PAGEUUID = "x-pageUuid";
    public static final String X_BANGBANG_DEVICEAGENT = "x-bangbang-deviceAgent";
    public static final String X_USERID = "x-userId";
}