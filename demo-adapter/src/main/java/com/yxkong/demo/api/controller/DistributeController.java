package com.yxkong.demo.api.controller;

import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.api.convert.ContextFactory;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.executor.AuthDistributeExecutor;
import com.yxkong.demo.infrastructure.common.annotation.RequiredToken;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 流程分发
 * @Author: yxkong
 * @Date: 2021/7/14 6:26 下午
 * @version: 1.0
 */
@Api(tags = "流程分发")
@RestController
@RequestMapping("/distribute")
public class DistributeController {
    @Resource
    private AuthDistributeExecutor authDistributeExecutor;

    @ApiOperation(value = "分发接口")
    @ApiResponses({@ApiResponse(code = 1, message = "1:成功;其它状态失败")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/route")
    @RequiredToken
    public ResultBean<DistributeDTO> route() {
        LoginToken loginToken = LoginTokenUtil.getLoginToken();
        /**
         * 构建领域上下文
         */
        final DistributeContext context = ContextFactory.distributeContext(loginToken);

        return authDistributeExecutor.executor(context);
    }
}