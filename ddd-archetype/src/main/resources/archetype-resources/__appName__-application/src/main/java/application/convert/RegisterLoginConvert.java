#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.convert;

import ${package}.application.context.account.RegisterAppContext;
import ${package}.domain.dto.context.LoginContext;
import ${package}.domain.dto.context.RegisterContext;
import ${package}.domain.model.user.PwdObject;

import java.util.Objects;

/**
 * 注册登录转换器
 * @Author: ${author}
 * @Date: 2021/11/15 10:56 PM
 * @version: ${version}
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
