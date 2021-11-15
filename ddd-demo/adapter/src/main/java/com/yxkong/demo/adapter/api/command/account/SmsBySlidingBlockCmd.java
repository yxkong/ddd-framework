package com.yxkong.demo.adapter.api.command.account;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 滑块发送验证码
 *
 * @Author: yxkong
 * @Date: 2021/11/15 10:08 AM
 * @version: 1.0
 */
@ApiModel("滑块发送验证码")
@Data
@NoArgsConstructor
public class SmsBySlidingBlockCmd extends SlidingBlockBase{
}
