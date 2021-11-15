package com.yxkong.demo.adapter.api.convert;

import com.yxkong.demo.adapter.api.command.account.RegisterCmd;
import com.yxkong.demo.application.context.account.RegisterContext;
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

    public static RegisterContext create(RegisterCmd register, HttpServletRequest request){
        String requestIp = WebUtil.getIpAddress(request);
        /**
         * 根据注册类型和环境推测 注册方式
         * 最终会有两种注册方式
         *    1，验证码注册
         *    2，密码注册
         */
        return new RegisterContext(register.getTenantId(),register.getMobile(),register.getVerifyCode(),requestIp, register.getPwd());
    }
}
