package com.yxkong.demo.application.convert;

import com.yxkong.demo.application.context.account.RegisterAppContext;
import com.yxkong.demo.domain.dto.context.RegisterContext;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/11/15 10:56 PM
 * @version: 1.0
 */
public class RegisterConvert {
    public static RegisterContext convert(RegisterAppContext registerAppContext,String md5Pwd,String salt,String uuid){
        RegisterContext.builder().userObject(registerAppContext.getUser())
                .requestIp(registerAppContext.getRequestIp())
        return null;
    }
}
