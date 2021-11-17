package com.yxkong.demo.adapter.api.controller;

import com.yxkong.demo.adapter.api.convert.ContextFactory;
import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.application.executor.AuthDistributeExecutor;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import com.yxkong.demo.domain.dto.resp.DistributeDTO;
import com.yxkong.demo.infrastructure.common.annotation.RequiredToken;
import com.yxkong.demo.infrastructure.common.util.LoginTokenUtil;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 授信流程分发
 *
 * @Author: yxkong
 * @Date: 2021/6/3 10:52 上午
 * @version: 1.0
 */
@Api(tags = "授信路由")
@RestController
@RequestMapping("/demo/distribute")
public class AuthDistributeController {

    @Resource
    private AuthDistributeExecutor authDistributeExecutor;

    @ApiOperation(value = "授信分发接口")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/route")
    @RequiredToken
    public ResultBean<DistributeDTO> route() {
        LoginToken loginToken = LoginTokenUtil.getLoginToken();
        /**
         * 构建应用上下文
         *    如果只是单个领域，这里表示的是单个领域上下文，领域上下文可以穿透到这层
         *    如果是多个领域的聚合，这里表示的应用上下文，综合了多个领域
         */
        final DistributeContext context = ContextFactory.distributeContext(loginToken);

        return authDistributeExecutor.executor(context);
    }
}