#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.controller;

import ${package}.adapter.api.command.AccountInfoQuery;
import ${package}.adapter.api.command.AccountSaveCommand;
import ${package}.infrastructure.common.util.LoginTokenUtil;
import ${package}.infrastructure.persistence.entity.${appName}.AccountDO;
import ${package}.infrastructure.persistence.mapper.${appName}andx.AccountMapper;
import ${groupId}.common.entity.dto.ResultBean;
import ${groupId}.common.util.ResultBeanUtil;
import io.swagger.annotations.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/10/28 11:27 AM
 * @version: ${version}
 */
@Api(tags = "account测试")
@RestController
@RequestMapping("/test/account")
public class AccountController {

    @Resource
    private AccountMapper accountMapper;
    @ApiOperation(value = "获取个人信息")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/info")
    public ResultBean info(@RequestBody@Validated AccountInfoQuery query) {
        LoginTokenUtil.reloadTenantId(query.getTenantId());
        AccountDO accountDO = accountMapper.findByMobile(query.getMobile());
        return ResultBeanUtil.success(accountDO);
    }
    @ApiOperation(value = "保存个人信息")
    @ApiResponses({@ApiResponse(code = 1, message = "")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "header", name = "token", value = "用户登录信息（token信息 json格式）", required = true, dataType = "string")})
    @PostMapping(value = "/save")
    public ResultBean save(@RequestBody@Validated AccountSaveCommand command){
        LoginTokenUtil.reloadTenantId(command.getTenantId());
        accountMapper.insertSelective( command.getAccountDo());
        return ResultBeanUtil.success(null);

    }

}
