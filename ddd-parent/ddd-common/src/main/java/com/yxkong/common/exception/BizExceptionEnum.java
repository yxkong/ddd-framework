package com.yxkong.common.exception;

/**
 * 具体的异常枚举类
 *
 * @author yxkong
 * @date 2019/5/27-19:38
 */
public enum BizExceptionEnum implements BaseResult {

    /**
     * 未实名
     */
    NO_REAL_NAME("1100", "用户未实名"),

    /**
     * 期限缓存表数据为空
     */
    PERIOD_MAP_IS_NULL("1000", "期限缓存表数据为空"),
    /**
     * 引导贷款超市
     */
    CAPITAL_NAME_IS_NULL("0002", "贷款超市"),

    GUIDE_FAIL("1000", "引导规则未通过"),

    FORBID_STATUS("1003", "停留在当前页面"),

    STATUS_TOKEN_INVALID("1008", "token失效，请重新登录!"),

    AUTH_VALIDATE_ERROR("1001", "授信项校验异常"),

    PAYMENT_METHOD_IS_NULL("1000", "该机构没有支付方式"),

    AUTH_TASK_CALLBACK("0", "风控已回流"),

    DO_NOT_REPEAT("0", "请勿重复操作!"),

    CHECK_MEMBER_FAILED("2000","非会员"),

    CHECK_MEMBER_SUCCESS("2001","会员"),

    HANDLING_ORDER("2003","该用户有处理中的订单!"),
    
    CHECK_BUY_AMONUT_FAILED("2004","购买金额校验未通过!"),
    
    CHECK_SMS_CODE_FAILED("2005","短信验证码校验未通过!"),
    
    CHECK_SIGN_ACCOUNT_FAILED("2006","签约校验未通过!"),

    CHECK_BUY_PATH_FAILED("2007", "流程校验未通过!"),
    VERIFY_CODE_EXCEED("2008","当日发送短信验证码超过限制"),
    SEND_VERIFY_CODE_FAILED("2009","发送短信失败"),
    PAY_APPLY_FAILED("2010","支付申请失败!"),
    NO_ORDER_INFO("2011","未查到订单信息"),
    VIP_CUSTOMER_FLAG("2012","该用户已经是会员!"),
    VIP_ORDER_SWITCH_CLOSE("2013","系统维护中"),
    CHECK_SMS_CODE_TIMEOUT("2014","验证码已过期，请重新获取验证码"),
    BANK_CARD_FAILED("2015","手机号与银行预留手机不符，请重新绑卡或换卡支付");
	
	
    BizExceptionEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private String code;

    private String message;

    @Override
    public String getStatus() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
