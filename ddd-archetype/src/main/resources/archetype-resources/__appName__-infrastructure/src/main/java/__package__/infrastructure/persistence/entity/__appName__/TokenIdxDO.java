#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.persistence.entity.${appName};

 import java.io.Serializable;
import java.util.Date;
/**
 *
 * @类介绍 token索引表
 * @time 2021年11月27日 10:48:58
 * @version ${version}
 *
 **/

@SuppressWarnings("serial")
public class TokenIdxDO implements Serializable  {
  
    
    /**
     * 主键id
     */
    private Long id;
    /**
     * 用户唯一标识,使用分布式id，相对有序
     */
    private Long uuid;
    /**
     * 用户token key
     */
    private String token;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updatedTime;

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
	 * 设置用户token key
	 */
    public void setToken(String token) {
        this.token = token;
    }
    /**
     * 获取用户token key
     */
    public String getToken() {
        return this.token;
    }
	/**
	 * 设置用户手机号
	 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取用户手机号
     */
    public String getMobile() {
        return this.mobile;
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
	/**
	 * 设置修改时间
	 */
    public void setUpdatedTime(Date updatedTime) {
        this.updatedTime = updatedTime;
    }
    /**
     * 获取修改时间
     */
    public Date getUpdatedTime() {
        return this.updatedTime;
    }

}