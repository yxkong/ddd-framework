package com.yxkong.demo.infrastructure.persistence.entity.demo;

 import java.io.Serializable;
import java.util.Date;
/**
 *
 * @类介绍 发送短信记录
 * @time 2021年11月24日 10:59:44
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class SmsLogDO implements Serializable  {
  
    
    /**
     * 
     */
    private Long id;
    /**
     * 用户来源渠道
     */
    private String proId;
    /**
     * 短信验证码
     */
    private String verifyCode;
    /**
     * 发送验证码手机号
     */
    private String mobile;
    /**
     * 短信类型，1 注册，2重置登录密码，3重置支付密码，4绑卡
     */
    private String smsType;
    /**
     * 验证码发送状态，0初始状态，1成功，-1失败,-2系统异常
     */
    private Integer sendStatus;
    /**
     * 使用状态，0未使用，1已使用
     */
    private Integer useStatus;
    /**
     * 
     */
    private String smsContent;
    /**
     * 回执
     */
    private String result;
    /**
     * 发送验证的时间
     */
    private Date sendTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 请求ip
     */
    private String requestIp;
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
	 * 设置用户来源渠道
	 */
    public void setProId(String proId) {
        this.proId = proId;
    }
    /**
     * 获取用户来源渠道
     */
    public String getProId() {
        return this.proId;
    }
	/**
	 * 设置短信验证码
	 */
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }
    /**
     * 获取短信验证码
     */
    public String getVerifyCode() {
        return this.verifyCode;
    }
	/**
	 * 设置发送验证码手机号
	 */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    /**
     * 获取发送验证码手机号
     */
    public String getMobile() {
        return this.mobile;
    }
	/**
	 * 设置短信类型，1 注册，2重置登录密码，3重置支付密码，4绑卡
	 */
    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }
    /**
     * 获取短信类型，1 注册，2重置登录密码，3重置支付密码，4绑卡
     */
    public String getSmsType() {
        return this.smsType;
    }
	/**
	 * 设置验证码发送状态，0初始状态，1成功，-1失败,-2系统异常
	 */
    public void setSendStatus(Integer sendStatus) {
        this.sendStatus = sendStatus;
    }
    /**
     * 获取验证码发送状态，0初始状态，1成功，-1失败,-2系统异常
     */
    public Integer getSendStatus() {
        return this.sendStatus;
    }
	/**
	 * 设置使用状态，0未使用，1已使用
	 */
    public void setUseStatus(Integer useStatus) {
        this.useStatus = useStatus;
    }
    /**
     * 获取使用状态，0未使用，1已使用
     */
    public Integer getUseStatus() {
        return this.useStatus;
    }
	/**
	 * 设置
	 */
    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }
    /**
     * 获取
     */
    public String getSmsContent() {
        return this.smsContent;
    }
	/**
	 * 设置回执
	 */
    public void setResult(String result) {
        this.result = result;
    }
    /**
     * 获取回执
     */
    public String getResult() {
        return this.result;
    }
	/**
	 * 设置发送验证的时间
	 */
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
    /**
     * 获取发送验证的时间
     */
    public Date getSendTime() {
        return this.sendTime;
    }
	/**
	 * 设置更新时间
	 */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    /**
     * 获取更新时间
     */
    public Date getUpdateTime() {
        return this.updateTime;
    }
	/**
	 * 设置请求ip
	 */
    public void setRequestIp(String requestIp) {
        this.requestIp = requestIp;
    }
    /**
     * 获取请求ip
     */
    public String getRequestIp() {
        return this.requestIp;
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