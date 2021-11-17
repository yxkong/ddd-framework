package com.yxkong.demo.adapter.api.command.account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/**
 * 密码注册
 * @Author: yxkong
 * @Date: 2021/11/17 2:32 PM
 * @version: 1.0
 */
@ApiModel("密码注册")
@Data
@NoArgsConstructor
public class RegisterWithPwdCmd extends RegisterWithoutPwdCmd {
    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String pwd;
}
