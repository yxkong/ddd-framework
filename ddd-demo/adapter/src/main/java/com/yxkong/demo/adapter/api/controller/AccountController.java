package com.yxkong.demo.adapter.api.controller;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.adapter.api.command.account.LonginWithPwdCmd;
import com.yxkong.demo.adapter.api.command.account.RegisterWithPwdCmd;
import com.yxkong.demo.adapter.api.command.account.RegisterWithoutPwdCmd;
import com.yxkong.demo.adapter.api.convert.RegisterFactory;
import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.application.executor.AccountExecutor;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 用户接口
 *
 * @Author: yxkong
 * @Date: 2021/11/15 11:06 AM
 * @version: 1.0
 */
@Api(tags = "注册demo")
@RestController
@RequestMapping("/demo/account")
public class AccountController {

    @Resource
    private AccountExecutor executor;

    @ApiOperation(value = "无密码注册接口")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/registerWithoutPwd")
    public ResultBean registerWithoutPwd(@RequestBody @Validated RegisterWithoutPwdCmd register, HttpServletRequest request) {
        LoginTokenUtil.reloadTenantId(register.getTenantId());
        RegisterAppContext context = RegisterFactory.create(register, request);
        return executor.register(context);
    }

    @ApiOperation(value = "有密码注册接口")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/registerWithPwd")
    public ResultBean registerWithPwd(@RequestBody @Validated RegisterWithPwdCmd register, HttpServletRequest request) {
        LoginTokenUtil.reloadTenantId(register.getTenantId());
        RegisterAppContext context = RegisterFactory.create(register, request);
        return executor.register(context);
    }

    @ApiOperation(value = "无密码登录接口")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/loginWithoutPwd")
    public ResultBean loginWithoutPwd(@RequestBody @Validated RegisterWithoutPwdCmd register, HttpServletRequest request) {
        LoginTokenUtil.reloadTenantId(register.getTenantId());
        RegisterAppContext context = RegisterFactory.create(register, request);
        return executor.loginWithoutPwd(context);
    }

    @ApiOperation(value = "有密码登录接口")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/loginWithPwd")
    public ResultBean loginWithPwd(@RequestBody @Validated LonginWithPwdCmd longinWithPwdCmd, HttpServletRequest request) {
        LoginTokenUtil.reloadTenantId(longinWithPwdCmd.getTenantId());
        RegisterAppContext context = RegisterFactory.create(longinWithPwdCmd, request);
        return executor.loginByPwd(context);
    }
    @ApiOperation(value = "获取account日志")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/accountLog")
    public ResultBean accountLog() {
        return executor.accountLog();
    }
}
