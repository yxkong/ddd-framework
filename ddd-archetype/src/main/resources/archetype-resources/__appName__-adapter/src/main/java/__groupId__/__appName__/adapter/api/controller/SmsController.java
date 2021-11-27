#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.controller;

import com.yxkong.common.entity.dto.ResultBean;
import ${package}.adapter.api.command.account.SmsBySlidingBlockCmd;
import ${package}.application.executor.SmsExecutor;
import ${package}.domain.dto.context.SlidingBlockContext;
import ${package}.domain.dto.resp.DistributeDTO;
import ${package}.infrastructure.common.util.LoginTokenUtil;
import ${package}.infrastructure.common.util.WebUtil;
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
 * @Author: ${author}
 * @Date: 2021/11/15 11:12 AM
 * @version: ${version}
 */
@Api(tags = "注册${appName}")
@RestController
@RequestMapping("/${appName}/sms")
public class SmsController {
    @Resource
    private SmsExecutor smsExecutor;
    @ApiOperation(value = "验证滑块发送短信验证码")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @PostMapping(value = "/sendBySlidingBlock")
    public ResultBean sendBySlidingBlock(@RequestBody @Validated SmsBySlidingBlockCmd smsCmd, HttpServletRequest request) {
        LoginTokenUtil.reloadTenantId(smsCmd.getTenantId());
        String requestIp = WebUtil.getIpAddress(request);
        SlidingBlockContext context = new SlidingBlockContext(smsCmd.getTenantId(),smsCmd.getMobile(), smsCmd.getSlidingBlockId(), smsCmd.getSlidingBlockSupplier(),requestIp,smsCmd.getSmsType());
        context.setProId(smsCmd.getProId());
        return smsExecutor.sendBySlidingBlock(context);
    }
}
