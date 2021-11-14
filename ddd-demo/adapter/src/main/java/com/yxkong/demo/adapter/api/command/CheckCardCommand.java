package com.yxkong.demo.adapter.api.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 验卡参数
 *
 * @Author: yxkong
 * @Date: 2021/6/7 3:44 下午
 * @version: 1.0
 */
@Data
@ApiModel("验卡参数")
public class CheckCardCommand {
    
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "银行卡号", required = true)
    @Length(min=10,max=20)
    private String cardNo;

    @ApiModelProperty(value = "银行卡类型cardType", required = true)
    private String cardType;

    @ApiModelProperty(value = "图形验证码 ")
    @Deprecated
    private String verifyCode;
}
