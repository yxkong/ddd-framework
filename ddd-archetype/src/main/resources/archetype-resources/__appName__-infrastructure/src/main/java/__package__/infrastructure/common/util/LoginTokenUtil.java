#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.util;

import ${package}.infrastructure.common.plugin.token.SecurityContext;
import ${package}.infrastructure.common.plugin.token.SecurityContextHolder;
import ${package}.infrastructure.common.plugin.token.SecurityContextImpl;
import ${groupId}.common.constant.TenantEnum;
import ${groupId}.common.entity.common.LoginToken;

import java.util.Objects;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 11:36 上午
 * @version: ${version}
 */
public class LoginTokenUtil {

    public static LoginToken getLoginToken() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getLoinToken();
    }
    public static Integer getTenantId() {
        LoginToken loginToken= getLoginToken();
        if (Objects.nonNull(loginToken) && loginToken.getTenantId()!= null) {
            return loginToken.getTenantId();
        }
        // 返回一个默认的
        return TenantEnum.getDefault().getTenantId();
    }

    /**
     * 当前线程重现放租户id
     * @param tenantId
     */
    public static void reloadTenantId(Integer tenantId) {
        SecurityContextHolder.clearContext();
        LoginToken loginToken = new LoginToken();
        loginToken.setTenantId(tenantId);
        SecurityContextHolder.setContext(new SecurityContextImpl(loginToken));
    }

    /**
     * 当前线程重放loginToken
     * @param loginToken
     */
    public static void reloadLoginToken(LoginToken loginToken) {
        SecurityContextHolder.clearContext();
        SecurityContext context = new SecurityContextImpl(loginToken);
        SecurityContextHolder.setContext(context);
    }

    /**
     * 对loginInfo进行校验,防止拦截器塞入一个空对象，是否登录校验用户登录的mobile</br>
     * 如果用户token存在，则手机号一定存在
     * @param loginToken
     * @return
     */
    public static boolean validateToken(LoginToken loginToken) {
        if (Objects.nonNull(loginToken) && Objects.nonNull(loginToken.getMobile()) && loginToken.getMobile().length() > 0) {
            return true;
        }
        return false;
    }
    /**
     * 校验用户是否实名，如果已实名返回true,未实名返回false</br>
     *
     * @param: @param loginInfo
     * @param: @return
     */
    public static boolean validateRealName(LoginToken loginToken) {
        if (Objects.nonNull(loginToken) && loginToken.isRealName()) {
            return true;
        }
        return false;
    }

    /**
     * 获取redis的key
     * @param token
     * @return
     */
    public static String getKey(String token){
        return "userApi:token:"+token;
    }
}