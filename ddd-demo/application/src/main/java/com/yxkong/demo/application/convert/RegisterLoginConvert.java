package com.yxkong.demo.application.convert;

import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.domain.dto.context.LoginContext;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.model.user.PwdObject;

import java.util.Objects;

/**
 * 注册登录转换器
 * @Author: yxkong
 * @Date: 2021/11/15 10:56 PM
 * @version: 1.0
 */
public class RegisterLoginConvert {
    /**
     * 注册转换器
     * @param registerAppContext
     * @param md5Pwd
     * @param salt
     * @param uuid
     * @return
     */
    public static RegisterContext convert(RegisterAppContext registerAppContext,String md5Pwd,String salt,long uuid){
        return RegisterContext.builder().userObject(registerAppContext.getUser())
                .requestIp(registerAppContext.getRequestIp())
                .env(registerAppContext.getEnv())
                .proId(registerAppContext.getProId())
                .pwdObject(new PwdObject(salt,md5Pwd))
                .uuid(uuid)
                .build();
    }

    /**
     * 登录转换器
     * @param registerAppContext
     * @return
     */
    public static LoginContext convert(RegisterAppContext registerAppContext){
        LoginContext context =  LoginContext.builder().userObject(registerAppContext.getUser())
                .requestIp(registerAppContext.getRequestIp())
                .env(registerAppContext.getEnv())
                .proId(registerAppContext.getProId())
                .pwd(registerAppContext.getPwd())
                .build();
        context.setLoginType(0);
        if (Objects.nonNull(context.getPwd())){
            context.setLoginType(1);
        }
        return context;
    }
}
