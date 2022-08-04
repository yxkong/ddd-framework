package com.yxkong.common.entity.common;

import java.io.Serializable;
import java.util.Objects;

/**
 * 用户登录token信息
 *
 * @Author: yxkong
 * @Date: 2021/6/2 11:05 上午
 * @version: 1.0
 */
public class LoginToken implements Serializable {

    private static final long serialVersionUID = 1L;

    public final static String DEFULT_TOKEN = "token";
    private String token;
    /**
     * 当前登陆手机号
     */

    private String mobile;
    /**
     * 实名客户id
     */
    private Long customerId;
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 用户的uuid
     */
    private Long uuid;
    /**
     * 姓名
     */
    private String realName;
    /**
     * 身份证号
     */
    private String certId;
    /**
     * 客户性别：0 女生 1男生
     */
    private String sex;
    /**
     * 客户生日
     */
    private String birthday;
    /**
     * 登录方式 1,密码； 0，sms；2，联合登录；
     */
    private Integer loginType;

    /**
     * 登录时间
     */
    private String loginTime;
    /**
     * 用户来源，app、h5、weixin等
     */
    private String source;

    /**
     * imei
     */
    private String imei;
    /**
     * 登录渠道
     */
    private String loginProId;
    /**
     * 当前操作渠道，直接从header中获取
     */
    private String proId;
    /**
     * 租户id
     */
    private String registerTime;
    /**
     * 租户
     */
    private Integer tenantId;

    public LoginToken() {
    }

    public LoginToken(String token, String mobile, Long accountId, Long uuid, Integer tenant) {
        this.token = token;
        this.mobile = mobile;
        this.accountId = accountId;
        this.uuid = uuid;
        this.tenantId = tenantId;
    }

    /**
     * 是否实名
     *
     * @return
     */
    public Boolean isRealName() {
        if (Objects.isNull(customerId) || customerId == 0L) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * 是否有效token
     *
     * @return
     */
    public Boolean isValid() {
        if (Objects.isNull(mobile)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCertId() {
        return certId;
    }

    public void setCertId(String certId) {
        this.certId = certId;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Integer getLoginType() {
        return loginType;
    }

    public void setLoginType(Integer loginType) {
        this.loginType = loginType;
    }

    public Long getUuid() {
        return uuid;
    }

    public void setUuid(Long uuid) {
        this.uuid = uuid;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }



    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }


    public String getLoginProId() {
        return loginProId;
    }

    public void setLoginProId(String loginProId) {
        this.loginProId = loginProId;
    }

    public String getProId() {
        return proId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }
}