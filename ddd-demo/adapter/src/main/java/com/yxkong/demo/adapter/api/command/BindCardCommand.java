package com.yxkong.demo.adapter.api.command;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 绑卡入参
 *
 * @Author: yxkong
 * @Date: 2021/6/10 4:27 下午
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@ApiModel("绑卡参数")
public class BindCardCommand {

    @NotNull
    @Length(min = 10, max = 20)
    @ApiModelProperty(value = "银行卡号", required = true)
    private String cardNo;
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "银行编码", required = true)
    private String bankCode;
    @NotNull
    @NotBlank
    @ApiModelProperty(value = "银行预留手机号", required = true)
    private String phone;
    @ApiModelProperty(value = "授信位置,1,原流程，4，列表页，5定制化，6 新流程 ")
    private Integer authSource;
    @ApiModelProperty(value = "设备指纹，用于推送设备指纹数据 ")
    private String userData;
    @ApiModelProperty(value = "设备指纹 mm")
    private String tick;
    @ApiModelProperty(value = "设备指纹 bqs")
    private String tokenKey;
}
