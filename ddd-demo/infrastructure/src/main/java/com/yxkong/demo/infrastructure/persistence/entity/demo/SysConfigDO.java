package com.yxkong.demo.infrastructure.persistence.entity.demo;

 import java.io.Serializable; 
import java.util.Date;
/**
 *
 * @类介绍 系统配置项表
 * @time 2021年10月26日 16:37:31
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class SysConfigDO implements Serializable  {
  
    
    /**
     * 
     */
    private Long id;
    /**
     * 配置项key
     */
    private String optionKey;
    /**
     * 
     */
    private String optionValue;
    /**
     * 值在java中的类型
     */
    private String valueType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 1 启用  0 禁用
     */
    private Integer status;
    /**
     * 使用系统
     */
    private String system;
    /**
     * 添加人
     */
    private String creator;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新人
     */
    private String updator;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 租户id
     */
    private Integer tenantId;

	/**
	 * 设置
	 */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取
     */
    public Long getId() {
        return this.id;
    }
	/**
	 * 设置配置项key
	 */
    public void setOptionKey(String optionKey) {
        this.optionKey = optionKey;
    }
    /**
     * 获取配置项key
     */
    public String getOptionKey() {
        return this.optionKey;
    }
	/**
	 * 设置
	 */
    public void setOptionValue(String optionValue) {
        this.optionValue = optionValue;
    }
    /**
     * 获取
     */
    public String getOptionValue() {
        return this.optionValue;
    }
	/**
	 * 设置值在java中的类型
	 */
    public void setValueType(String valueType) {
        this.valueType = valueType;
    }
    /**
     * 获取值在java中的类型
     */
    public String getValueType() {
        return this.valueType;
    }
	/**
	 * 设置备注
	 */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    /**
     * 获取备注
     */
    public String getRemark() {
        return this.remark;
    }
	/**
	 * 设置1 启用  0 禁用
	 */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取1 启用  0 禁用
     */
    public Integer getStatus() {
        return this.status;
    }
	/**
	 * 设置使用系统
	 */
    public void setSystem(String system) {
        this.system = system;
    }
    /**
     * 获取使用系统
     */
    public String getSystem() {
        return this.system;
    }
	/**
	 * 设置添加人
	 */
    public void setCreator(String creator) {
        this.creator = creator;
    }
    /**
     * 获取添加人
     */
    public String getCreator() {
        return this.creator;
    }
	/**
	 * 设置创建时间
	 */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    /**
     * 获取创建时间
     */
    public Date getCreateDate() {
        return this.createDate;
    }
	/**
	 * 设置更新人
	 */
    public void setUpdator(String updator) {
        this.updator = updator;
    }
    /**
     * 获取更新人
     */
    public String getUpdator() {
        return this.updator;
    }
	/**
	 * 设置修改时间
	 */
    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    /**
     * 获取修改时间
     */
    public Date getUpdateDate() {
        return this.updateDate;
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

}