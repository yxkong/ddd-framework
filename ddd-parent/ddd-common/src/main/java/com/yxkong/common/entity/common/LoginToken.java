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
    public static final String LOGIN_TYPE_H5 = "h5";
    public static final String LOGIN_TYPE_WEIXIN = "weixin";
    public static final String LOGIN_TYPE_APP = "app";
    public static final String LOGIN_TYPE_UNION = "union";

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
     * 没有赋值
     */
    private String loginType;
    /**
     * 集团用户中心返回的uuid
     */
    private String uuid;
    /**
     * 没有赋值
     */
    private String loginTime;
    /**
     * 加密的用户来源，说明decodeSource
     */
    private String source;
    /**
     * 用户来源0：万卡，1：叮当，2：闪白条，3：品质商场，" +
     * "4：钱包，5：超能二类，6：超能5类，7：超能地推，" +
     * "8：向阳花，9：ofo，10：白条，1000以上：三体定制化来源
     */
    private Integer decodeSource;
    /**
     * 集团用户中心客户号
     */
    private String custNo;
    private Integer isBack;
    /**
     * imei
     */
    private String imei;
    /**
     * 用户标签
     */
    private String lable;
    /**
     * 路由化标识0:老用户，1 路由化用户 2 额度三体化用户
     */
    private Integer isRoute;
    /**
     * 没有赋值
     */
    private String loginProId;
    /**
     * 当前操作渠道，在loginProId没值时使用
     */
    private String proId;
    private String createTime;
    /**
     * 租户id：万卡 1001 小房卡 2001 亿贝卡 2005
     */
    private Integer tenantId;

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

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
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

    public Integer getDecodeSource() {
        return decodeSource;
    }

    public void setDecodeSource(Integer decodeSource) {
        this.decodeSource = decodeSource;
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public Integer getIsBack() {
        return isBack;
    }

    public void setIsBack(Integer isBack) {
        this.isBack = isBack;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public Integer getIsRoute() {
        return isRoute;
    }

    public void setIsRoute(Integer isRoute) {
        this.isRoute = isRoute;
    }

    public String getLoginProId() {
        return loginProId;
    }

    public void setLoginProId(String loginProId) {
        this.loginProId = loginProId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    /**
     * 优先使用loginProId，如果没有，则用proid
     *
     * @return
     */
    public String getProId() {
        if (Objects.isNull(this.loginProId)) {
            return proId;
        }
        return this.loginProId;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}