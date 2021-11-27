#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.persistence.entity.${appName};

 import java.io.Serializable;
import java.util.Date;
/**
 *
 * @类介绍 联合账户表
 * @time 2021年11月11日 17:25:39
 * @version ${version}
 *
 **/

@SuppressWarnings("serial")
public class AccountLogDO implements Serializable  {
  
    
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户唯一标识,使用分布式id，相对有序
     */
    private Long uuid;
    /**
     * 业务类型，0，注册，1,登录，2，改密，3注销，4，绑定账户
     */
    private Integer bizType;
    /**
     * 操作环境，pc/h5/weixin/huawei/xiaomi/oppo/vivo/ios ...
     */
    private String env;
    /**
     * 操作渠道
     */
    private String proId;
    /**
     * 操作ip
     */
    private String requestIp;
    /**
     * 租户id
     */
    private Integer tenantId;
    /**
     * 创建时间
     */
    private Date createTime;

	/**
	 * 设置主键id
	 */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取主键id
     */
    public Long getId() {
        return this.id;
    }
	/**
	 * 设置用户唯一标识,使用分布式id，相对有序
	 */
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    /**
     * 获取用户唯一标识,使用分布式id，相对有序
     */
    public Long getUuid() {
        return this.uuid;
    }
	/**
	 * 设置业务类型，0，注册，1,登录，2，改密，3注销，4，绑定账户
	 */
    public void setBizType(Integer bizType) {
        this.bizType = bizType;
    }
    /**
     * 获取业务类型，0，注册，1,登录，2，改密，3注销，4，绑定账户
     */
    public Integer getBizType() {
        return this.bizType;
    }
	/**
	 * 设置操作环境，pc/h5/weixin/huawei/xiaomi/oppo/vivo/ios ...
	 */
    public void setEnv(String env) {
        this.env = env;
    }
    /**
     * 获取操作环境，pc/h5/weixin/huawei/xiaomi/oppo/vivo/ios ...
     */
    public String getEnv() {
        return this.env;
    }
	/**
	 * 设置操作渠道
	 */
    public void setProId(String proId) {
        this.proId = proId;
    }
    /**
     * 获取操作渠道
     */
    public String getProId() {
        return this.proId;
    }
	/**
	 * 设置租户id
	 */
    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
    /**
     * 获取租户id
     */
    public Integer getTenantId() {
        return this.tenantId;
    }
	/**
	 * 设置创建时间
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    /**
     * 获取创建时间
     */
    public Date getCreateTime() {
        return this.createTime;
    }

    public String getRequestIp() {
        return requestIp;
    }

    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
}