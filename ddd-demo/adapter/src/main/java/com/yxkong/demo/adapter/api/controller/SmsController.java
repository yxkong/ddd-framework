package com.yxkong.demo.adapter.api.controller;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.adapter.api.command.account.SmsBySlidingBlockCmd;
import com.yxkong.demo.application.executor.SmsExecutor;
import com.yxkong.demo.domain.dto.context.SlidingBlockContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import com.yxkong.demo.infrastructure.common.util.WebUtil;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * 验证码发送
 *
 * @Author: yxkong
 * @Date: 2021/11/15 11:12 AM
 * @version: 1.0
 */
@Api(tags = "注册demo")
@RestController
@RequestMapping("/demo/sms")
public class SmsController {
    @Resource
    private SmsExecutor smsExecutor;
    @ApiOperation(value = "验证滑块发送短信验证码")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @PostMapping(value = "/sendBySlidingBlock")
    public ResultBean sendBySlidingBlock(@RequestBody @Validated SmsBySlidingBlockCmd smsCmd, HttpServletRequest request) {
        LoginTokenUtil.reloadTenantId(smsCmd.getTenantId());
        String requestIp = WebUtil.getIpAddress(request);
        SlidingBlockContext context = new SlidingBlockContext(smsCmd.getTenantId(),smsCmd.getMobile(), smsCmd.getSlidingBlockId(), smsCmd.getSlidingBlockSupplier(),requestIp);
        return smsExecutor.sendBySlidingBlock(context);
    }
}
