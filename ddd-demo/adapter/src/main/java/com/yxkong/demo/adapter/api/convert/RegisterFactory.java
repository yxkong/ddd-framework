package com.yxkong.demo.adapter.api.convert;

import com.yxkong.demo.adapter.api.command.account.RegisterWithPwdCmd;
import com.yxkong.demo.adapter.api.command.account.RegisterWithoutPwdCmd;
import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.infrastructure.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/15 7:05 PM
 * @version: 1.0
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
