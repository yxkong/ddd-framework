package com.yxkong.demo.infrastructure.persistence.entity.demo;

 import java.io.Serializable;
import java.util.Date;
/**
 *
 * @类介绍 账户表
 * @time 2021年11月11日 17:25:38
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class AccountDO implements Serializable  {
  
    
    /**
     * 账户id
     */
    private Long id;
    /**
     * 用户唯一标识
     */
    private Long uuid;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 登录密码
     */
    private String pwd;
    /**
     * 密码盐值
     */
    private String salt;
    /**
     * 账户状态,0启用，1禁用
     */
    private Integer status;
    /**
     * 租户id
     */
    private Integer tenantId;
    /**
     * 注册时渠道号
     */
    private String proId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updatedTime;

	/**
	 * 设置账户id
	 */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取账户id
     */
    public Long getId() {
        return this.id;
    }
	/**
	 * 设置用户唯一标识
	 */
    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }
    /**
     * 获取用户唯一标识
     */
    public Long getUuid() {
        return this.uuid;
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
	 * 设置登录密码
	 */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
    /**
     * 获取登录密码
     */
    public String getPwd() {
        return this.pwd;
    }
	/**
	 * 设置密码盐值
	 */
    public void setSalt(String salt) {
        this.salt = salt;
    }
    /**
     * 获取密码盐值
     */
    public String getSalt() {
        return this.salt;
    }
	/**
	 * 设置账户状态,0启用，1禁用
	 */
    public void setStatus(Integer status) {
        this.status = status;
    }
    /**
     * 获取账户状态,0启用，1禁用
     */
    public Integer getStatus() {
        return this.status;
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
	 * 设置注册时渠道号
	 */
    public void setProId(String proId) {
        this.proId = proId;
    }
    /**
     * 获取注册时渠道号
     */
    public String getProId() {
        return this.proId;
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