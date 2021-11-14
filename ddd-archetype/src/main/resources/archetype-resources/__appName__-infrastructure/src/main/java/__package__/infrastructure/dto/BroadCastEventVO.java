#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

/**
 * @Author: ${author}
 * @Date: 2021/6/21 6:22 下午
 * @version: ${version}
 */
@Data
@Builder
public class BroadCastEventVO {
    /**
     *
     */
    private static final long serialVersionUID = -5242805029911592245L;

    /**
     * 用户账户idaccountId
     */
    private Long accountId;
    /**
     * 设备类型
     */
    private String deviceType;
    /**
     * 设备唯一标识
     */
    private String deviceUuid;
    /**
     * 1.原流程  2.资质认证 3.结果页 4.列表页 5.定制化
     */
    private Integer authSource = 0;
    /**
     * 任务号  taskId
     */
    private String taskId;


    /**
     * header中的数据 accept
     */
    private String accept;
    /**
     * header中的数据 accept-charset
     */
    private String acceptCharset;
    /**
     * header中的数据 cache-control
     */
    private String cacheControl;
    /**
     * header中的数据 connection
     */
    private String connection;
    /**
     * header中的数据 content-length
     */
    private String contentLength;
    /**
     * header中的数据 content-type
     */
    private String contentType;
    /**
     * header中的数据 eagleeye-rpcid
     */
    private String eagleeyeRpcid;
    /**
     * header中的数据 host
     */
    private String host;
    /**
     * header中的数据 pragma
     */
    private String pragma;
    /**
     * header中的数据 remoteip
     */
    private String remoteip;
    /**
     * header中的数据 archetype-agent
     */
    private String userAgent;
    /**
     * header中的数据 web-server-type
     */
    private String webServerType;
    /**
     * header中的数据 wl-proxy-client-ip
     */
    private String wlProxyClientIp;
    /**
     * header中的数据 x-forwarded-cluster
     */
    private String xForwardedCluster;
    /**
     * header中的数据 x-forwarded-for
     */
    private String xForwardedFor;
    /**
     * header中的数据 x-forwarded-port
     */
    private String xForwardedPort;
    /**
     * header中的数据 x-forwarded-proto
     */
    private String xForwardedProto;
    /**
     * header中的数据 x-real-ip
     */
    private String xRealIp;
    /**
     * header中的数据version或wk_appversion   app版本
     */
    private String appVersion;

    /**
     * header中的数据wk_computer_system   操作系统版本
     */
    private String computerSystem;
    /**
     * header中的数据  wk_computer_type
     */
    private String computerType;
}