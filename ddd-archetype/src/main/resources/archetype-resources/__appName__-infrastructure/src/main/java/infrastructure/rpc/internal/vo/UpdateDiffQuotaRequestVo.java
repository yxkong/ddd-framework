#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.rpc.internal.vo;

import java.math.BigDecimal;
import java.util.Date;

import ${package}.infrastructure.common.util.CommonUtil;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateDiffQuotaRequestVo {
    /**
     * 客户号
     */
    private Long customerId;
    /**
     * 要提升或者降低的额度
     */
    private BigDecimal amt;
    /**
     * 类型,CLI:提额;CLD:降额;CHG:覆盖更新
     */
    @Builder.Default
    private String type = "CLI";
    /**
     * 是否是永久额度,1:是;0:否（临时）
     */
    private Integer isPerm;
    /**
     * 生效时间，比如：2018-08-08 17:30:25 null为当前时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date effectTime;
    /**
     * 失效时间，比如：2018-08-08 17:30:25 null为当前时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date exprieTime;
    /**
     * 第三方请求的唯一ID
     */
    @Builder.Default
    private String requestId = CommonUtil.ID_WORKER.nextId()+"";
    /**
     * 	备注
     */
    private String remark;
    /**
     * 更新标识，活动标识，记录活动，对接时请先沟通
     */
    @Builder.Default
    private String creator = "wkAuth";
    /**
     * 活动描述，前端展示，对接请先沟通
     */
    private String reason;
    /**
     * 渠道，对接时请先沟通
     */
    @Builder.Default
    private String channel = "wkAuth";
}