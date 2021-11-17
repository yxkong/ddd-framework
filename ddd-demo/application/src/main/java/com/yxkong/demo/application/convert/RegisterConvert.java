package com.yxkong.demo.application.convert;

import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.domain.dto.context.RegisterContext;

/**
 * 注册转换器
 * @Author: yxkong
 * @Date: 2021/11/15 10:56 PM
 * @version: 1.0
 */
public class RegisterConvert {
    public static RegisterContext convert(RegisterAppContext registerAppContext,String md5Pwd,String salt,long uuid){
        return RegisterContext.builder().userObject(registerAppContext.getUser())
                .requestIp(registerAppContext.getRequestIp())
                .env(registerAppContext.getEnv())
                .proId(registerAppContext.getProId())
                .salt(salt)
                .pwd(md5Pwd)
                .uuid(uuid)
                .build();
    }
}
