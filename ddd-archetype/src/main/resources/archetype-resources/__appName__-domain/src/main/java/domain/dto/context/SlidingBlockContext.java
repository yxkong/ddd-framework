#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.dto.context;

import com.yxkong.common.constant.TenantEnum;
import ${package}.domain.model.slidingblock.SlidingBlock;
import ${package}.domain.model.user.UserObject;
import lombok.Getter;

/**
 * 滑块验证上下文
 *
 * @Author: ${author}
 * @Date: 2021/11/15 1:41 PM
 * @version: ${version}
 */
@Getter
public class SlidingBlockContext {

    private SlidingBlock slidingBlock;
    private UserObject user;
    private String validate;
    private String requestIp;
    private String proId;
    private Integer smsType;
    public  SlidingBlockContext(Integer tenantId,String mobile,String slidingBlockId,String slidingBlockSupplier,String requestIp,Integer smsType){
        TenantEnum tenantEnum = TenantEnum.get(tenantId);
        this.slidingBlock = new SlidingBlock(tenantEnum,slidingBlockId,slidingBlockSupplier);
        this.user = new UserObject(mobile,tenantEnum);
        this.requestIp = requestIp;
        this.smsType = smsType;
    }

    /**
     * 易盾使用的二次校验参数
     * @param validate
     */
    public void setValidate(String validate) {
        this.validate = validate;
    }

    public void setProId(String proId) {
        this.proId = proId;
    }
}
