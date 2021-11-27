#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.api.convert;

import ${package}.adapter.api.command.account.LonginWithPwdCmd;
import ${package}.adapter.api.command.account.RegisterWithPwdCmd;
import ${package}.adapter.api.command.account.RegisterWithoutPwdCmd;
import ${package}.application.context.account.RegisterAppContext;
import ${package}.infrastructure.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/11/15 7:05 PM
 * @version: ${version}
 */
public class RegisterFactory {

    /**
     * 无密码注册上下文构建
     * @param register
     * @param request
     * @return
     */
    public static RegisterAppContext create(RegisterWithoutPwdCmd register, HttpServletRequest request){
        String requestIp = WebUtil.getIpAddress(request);
        return new RegisterAppContext(register.getTenantId(),register.getMobile(),register.getVerifyCode(),requestIp, null,register.getProId(), register.getEnv());
    }
    public static RegisterAppContext create(LonginWithPwdCmd register, HttpServletRequest request){
        String requestIp = WebUtil.getIpAddress(request);
        return new RegisterAppContext(register.getTenantId(),register.getMobile(),null,requestIp, null,register.getProId(), register.getEnv());
    }

    /**
     * 有密码注册上下文构建
     * @param register
     * @param request
     * @return
     */
    public static RegisterAppContext create(RegisterWithPwdCmd register, HttpServletRequest request){
        String requestIp = WebUtil.getIpAddress(request);
        return new RegisterAppContext(register.getTenantId(),register.getMobile(),register.getVerifyCode(),requestIp, register.getPwd(),register.getProId(), register.getEnv());
    }
}
