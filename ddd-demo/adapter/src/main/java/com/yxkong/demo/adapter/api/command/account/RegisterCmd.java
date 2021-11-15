package com.yxkong.demo.adapter.api.command.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 短信验证码注册
 *
 * @Author: yxkong
 * @Date: 2021/11/15 10:36 AM
 * @version: 1.0
 */
@ApiModel("短信验证码注册")
@Data
@NoArgsConstructor
public class RegisterCmd extends MobileBase{
    @ApiModelProperty("短信验证码")
    private String verifyCode;
    @ApiModelProperty("图形验证码")
    private String captcha;

    @ApiModelProperty(value = "密码")
    private String pwd;

    /**
     * 注册类型，1，验证码，2，密码
     */
    private Integer registerType;
    /**
     * 来源渠道
     */
    private String proId;
    /**
     * 环境
     */
    private String env;
}
