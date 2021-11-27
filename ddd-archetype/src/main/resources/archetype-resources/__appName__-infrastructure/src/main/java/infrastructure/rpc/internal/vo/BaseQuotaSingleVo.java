#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@ApiModel(value = "单场景额度返参基类")
@Data
public class BaseQuotaSingleVo {

    /**
     * 主键ID
     */
    @ApiModelProperty(value = "额度id")
    private Long id;
    /**
     * 万卡用户id 取余分表
     */
    @ApiModelProperty(value = "用户id")
    private Long customerId;
    /**
     * 总额度
     */
    @ApiModelProperty(value = "总额度")
    private BigDecimal totalAmt;
    
    /**
     * 总额度
     */
    // @ApiModelProperty(value = "风控utc临时增加的总额度")
    // private BigDecimal totalAmtForRisk;
    
    
    /**
     * 已用总额度
     */
    @ApiModelProperty(value = "已用总额度")
    private BigDecimal totalAmtUsed;
    /**
     * 激活状态，0,初始化，1激活
     */
    @ApiModelProperty(value = "激活状态，0,初始化，1激活")
    private Integer activeStatus;
    /**
     * 审核状态，0初始化（审核中），5激活，6拒绝
     */
    @ApiModelProperty(value = "审核状态，0初始化（审核中），5激活，6拒绝")
    private Integer appStatus;
    /**
     * 激活时间
     */
    @ApiModelProperty(value = "激活时间")
    private Date activeTime;
    /**
     * 冻结状态 0未冻结，1已冻结
     */
    @ApiModelProperty(value = "冻结状态 0未冻结，1已冻结")
    private Integer freezeStatus;
    /**
     * 冻结时间
     */
    @ApiModelProperty(value = "冻结时间")
    private Date freezeTime;
    /**
     * 冻结额度
     */
    @ApiModelProperty(value = "冻结额度")
    private BigDecimal freezeAmt;
    @ApiModelProperty(value = "临时额度冻结金额")
    private BigDecimal tempFreezeQuota;
    @ApiModelProperty(value = "固定额度冻结金额")
    private BigDecimal permFreezeQuota;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "姓名")
    private String name;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "证件号")
    private String certId;//证件号
    @ApiModelProperty(value = "卡号")
    private String cardNo;//卡号
    @ApiModelProperty(value = "来源")
    private Integer source;
    // @ApiModelProperty(value = "是否设置过交易密码")
    // private Boolean hasPwd;

    /**
     * 可用额度(总可用)
     */
    @ApiModelProperty(value = "可用额度(总可用)")
    private BigDecimal enableAmt;
    /**
     * 服务额度
     */
    @ApiModelProperty(value = "服务额度")
    private BigDecimal serviceAmt;
    @ApiModelProperty(value = "临时额度总额")
    private BigDecimal tempTotalQuota;
    @ApiModelProperty(value = "激活额度")
    private BigDecimal activeAmt;
    @ApiModelProperty(value = "临时额度最大过期时间，日期格式：yyyy-MM-dd HH:mm:ss")
    private Date tempMaxExpireTime;
    
    @ApiModelProperty(value = "临时额度最小过期时间，日期格式：yyyy-MM-dd HH:mm:ss")
    private Date tempMinExpireTime;
}