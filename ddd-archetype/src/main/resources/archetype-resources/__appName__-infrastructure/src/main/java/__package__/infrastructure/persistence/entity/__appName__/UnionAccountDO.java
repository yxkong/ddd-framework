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
public class UnionAccountDO implements Serializable  {
  
    
    /**
     * 主键id
     */
    private Long id;
    /**
     * 开放用户唯一标识
     */
    private String openUuid;
    /**
     * 开放平台
     */
    private String openPlatform;
    /**
     * 用户唯一标识,使用分布式id，相对有序
     */
    private Long uuid;
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
	 * 设置开放用户唯一标识
	 */
    public void setOpenUuid(String openUuid) {
        this.openUuid = openUuid;
    }
    /**
     * 获取开放用户唯一标识
     */
    public String getOpenUuid() {
        return this.openUuid;
    }
	/**
	 * 设置开放平台
	 */
    public void setOpenPlatform(String openPlatform) {
        this.openPlatform = openPlatform;
    }
    /**
     * 获取开放平台
     */
    public String getOpenPlatform() {
        return this.openPlatform;
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