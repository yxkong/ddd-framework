package com.yxkong.demo.adapter.api.command.account;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 手机信息
 *
 * @Author: yxkong
 * @Date: 2021/11/15 10:29 AM
 * @version: 1.0
 */
@Data
public class MobileBase {
    @NotNull(message = "手机号码不能为空")
    @Length(min = 11, max = 11,message = "手机号码长度必须是11位")
    @ApiModelProperty(value = "手机号码", required = true)
    String mobile;
    @NotNull(message = "租户id不能为空！")
    @Range(min = 1000, max = 3000, message = "range在[1000,3000]之间")
    @ApiModelProperty(value = "租户id", required = true)
    Integer tenantId;
}
