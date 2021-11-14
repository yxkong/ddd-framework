#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.persistence.entity.${appName};

 import java.io.Serializable; 
import java.util.Date;
/**
 *
 * @类介绍 账户表
 * @time 2021年10月26日 16:33:26
 * @version ${version}
 *
 **/

@SuppressWarnings("serial")
public class AccountDO implements Serializable  {
  
    
    /**
     * 账户id
     */
    private Long accountId;
    /**
     * 账户名(手机号或邮箱，唯一)
     */
    private String accountName;
    /**
     * 用户中心的uuid
     */
    private String uuid;
    /**
     * 关联身份证号码
     */
    private String certId;
    /**
     * 用户id
     */
    private Long customerId;
    /**
     * 用户中心客户id
     */
    private String custNo;
    /**
     * 账户状态,0启用，1禁用
     */
    private Integer status;
    /**
     * 账户来源
     */
    private Integer source;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updatedTime;
    /**
     * 实名时间
     */
    private Date realNameTime;
    /**
     * 判断用户是登陆还是注册进入万卡
     */
    private String isOld;
    /**
     * 是否激活：Y 激活，N 未激活
     */
    private String active;
    /**
     * 超能邀请码
     */
    private String inviteCode;
    /**
     * 运营商
     */
    private String operator;
    /**
     * 归属地
     */
    private String location;
    /**
     * 商铺ID
     */
    private String prodid;
    /**
     * 微信openID
     */
    private String openid;
    /**
     * 好友活动邀请码
     */
    private String inviteAcode;
    /**
     * 好友活动被邀请码
     */
    private String inviteRcode;
    /**
     * 放款媒体
     */
    private String utmMedium;
    /**
     * 放款活动
     */
    private String utmCampaign;
    /**
     * 极光推送id
     */
    private String registerId;
    /**
     * 最新登陆proId对应的
     */
    private String appType;
    /**
     * 0:原流程 1:授信路由化 2:三体路由化
     */
    private Integer isNew;
    /**
     * 只做小X卡用户来源标签
     */
    private String lable;
    /**
     * 用户MD5加密后的手机号
     */
    private String md5Mobile;
    /**
     * 租户id
     */
    private Integer tenantId;
    /**
     * 注册时渠道号
     */
    private String proId;
    /**
     * 微信公众号类型
     */
    private String wechatType;
    /**
     * 
     */
    private String proCertIdMD5;
    /**
     * 
     */
    private String proMobileMD5;
    /**
     * 手机号+身份证号 加密（给渠道使用）
     */
    private String proMobileCertId;

	/**
	 * 设置账户id
	 */
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    /**
     * 获取账户id
     */
    public Long getAccountId() {
        return this.accountId;
    }
	/**
	 * 设置账户名(手机号或邮箱，唯一)
	 */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    /**
     * 获取账户名(手机号或邮箱，唯一)
     */
    public String getAccountName() {
        return this.accountName;
    }
	/**
	 * 设置用户中心的uuid
	 */
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    /**
     * 获取用户中心的uuid
     */
    public String getUuid() {
        return this.uuid;
    }
	/**
	 * 设置关联身份证号码
	 */
    public void setCertId(String certId) {
        this.certId = certId;
    }
    /**
     * 获取关联身份证号码
     */
    public String getCertId() {
        return this.certId;
    }
	/**
	 * 设置用户id
	 */
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    /**
     * 获取用户id
     */
    public Long getCustomerId() {
        return this.customerId;
    }
	/**
	 * 设置用户中心客户id
	 */
    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }
    /**
     * 获取用户中心客户id
     */
    public String getCustNo() {
        return this.custNo;
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
	 * 设置账户来源
	 */
    public void setSource(Integer source) {
        this.source = source;
    }
    /**
     * 获取账户来源
     */
    public Integer getSource() {
        return this.source;
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
	/**
	 * 设置实名时间
	 */
    public void setRealNameTime(Date realNameTime) {
        this.realNameTime = realNameTime;
    }
    /**
     * 获取实名时间
     */
    public Date getRealNameTime() {
        return this.realNameTime;
    }
	/**
	 * 设置判断用户是登陆还是注册进入万卡
	 */
    public void setIsOld(String isOld) {
        this.isOld = isOld;
    }
    /**
     * 获取判断用户是登陆还是注册进入万卡
     */
    public String getIsOld() {
        return this.isOld;
    }
	/**
	 * 设置是否激活：Y 激活，N 未激活
	 */
    public void setActive(String active) {
        this.active = active;
    }
    /**
     * 获取是否激活：Y 激活，N 未激活
     */
    public String getActive() {
        return this.active;
    }
	/**
	 * 设置超能邀请码
	 */
    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode;
    }
    /**
     * 获取超能邀请码
     */
    public String getInviteCode() {
        return this.inviteCode;
    }
	/**
	 * 设置运营商
	 */
    public void setOperator(String operator) {
        this.operator = operator;
    }
    /**
     * 获取运营商
     */
    public String getOperator() {
        return this.operator;
    }
	/**
	 * 设置归属地
	 */
    public void setLocation(String location) {
        this.location = location;
    }
    /**
     * 获取归属地
     */
    public String getLocation() {
        return this.location;
    }
	/**
	 * 设置商铺ID
	 */
    public void setProdid(String prodid) {
        this.prodid = prodid;
    }
    /**
     * 获取商铺ID
     */
    public String getProdid() {
        return this.prodid;
    }
	/**
	 * 设置微信openID
	 */
    public void setOpenid(String openid) {
        this.openid = openid;
    }
    /**
     * 获取微信openID
     */
    public String getOpenid() {
        return this.openid;
    }
	/**
	 * 设置好友活动邀请码
	 */
    public void setInviteAcode(String inviteAcode) {
        this.inviteAcode = inviteAcode;
    }
    /**
     * 获取好友活动邀请码
     */
    public String getInviteAcode() {
        return this.inviteAcode;
    }
	/**
	 * 设置好友活动被邀请码
	 */
    public void setInviteRcode(String inviteRcode) {
        this.inviteRcode = inviteRcode;
    }
    /**
     * 获取好友活动被邀请码
     */
    public String getInviteRcode() {
        return this.inviteRcode;
    }
	/**
	 * 设置放款媒体
	 */
    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }
    /**
     * 获取放款媒体
     */
    public String getUtmMedium() {
        return this.utmMedium;
    }
	/**
	 * 设置放款活动
	 */
    public void setUtmCampaign(String utmCampaign) {
        this.utmCampaign = utmCampaign;
    }
    /**
     * 获取放款活动
     */
    public String getUtmCampaign() {
        return this.utmCampaign;
    }
	/**
	 * 设置极光推送id
	 */
    public void setRegisterId(String registerId) {
        this.registerId = registerId;
    }
    /**
     * 获取极光推送id
     */
    public String getRegisterId() {
        return this.registerId;
    }
	/**
	 * 设置最新登陆proId对应的
	 */
    public void setAppType(String appType) {
        this.appType = appType;
    }
    /**
     * 获取最新登陆proId对应的
     */
    public String getAppType() {
        return this.appType;
    }
	/**
	 * 设置0:原流程 1:授信路由化 2:三体路由化
	 */
    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }
    /**
     * 获取0:原流程 1:授信路由化 2:三体路由化
     */
    public Integer getIsNew() {
        return this.isNew;
    }
	/**
	 * 设置只做小X卡用户来源标签
	 */
    public void setLable(String lable) {
        this.lable = lable;
    }
    /**
     * 获取只做小X卡用户来源标签
     */
    public String getLable() {
        return this.lable;
    }
	/**
	 * 设置用户MD5加密后的手机号
	 */
    public void setMd5Mobile(String md5Mobile) {
        this.md5Mobile = md5Mobile;
    }
    /**
     * 获取用户MD5加密后的手机号
     */
    public String getMd5Mobile() {
        return this.md5Mobile;
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
	 * 设置微信公众号类型
	 */
    public void setWechatType(String wechatType) {
        this.wechatType = wechatType;
    }
    /**
     * 获取微信公众号类型
     */
    public String getWechatType() {
        return this.wechatType;
    }
	/**
	 * 设置
	 */
    public void setProCertIdMD5(String proCertIdMD5) {
        this.proCertIdMD5 = proCertIdMD5;
    }
    /**
     * 获取
     */
    public String getProCertIdMD5() {
        return this.proCertIdMD5;
    }
	/**
	 * 设置
	 */
    public void setProMobileMD5(String proMobileMD5) {
        this.proMobileMD5 = proMobileMD5;
    }
    /**
     * 获取
     */
    public String getProMobileMD5() {
        return this.proMobileMD5;
    }
	/**
	 * 设置手机号+身份证号 加密（给渠道使用）
	 */
    public void setProMobileCertId(String proMobileCertId) {
        this.proMobileCertId = proMobileCertId;
    }
    /**
     * 获取手机号+身份证号 加密（给渠道使用）
     */
    public String getProMobileCertId() {
        return this.proMobileCertId;
    }

}