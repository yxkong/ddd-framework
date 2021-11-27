#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.token;

import com.yxkong.common.entity.common.LoginToken;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 11:32 上午
 * @version: ${version}
 */
public class SecurityContextHolder {
    private static SecurityContextHolderStrategy strategy;
    static {
        initialize();
    }

    public static void clearContext() {
        strategy.clearContext();
    }

    public static SecurityContext getContext() {
        return strategy.getContext();
    }


    private static void initialize() {
        try {
            strategy = (new ThreadLocalSecurityContextHolderStrategy());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void setContext(SecurityContext context) {
        strategy.setContext(context);
    }
    public static void setLoginToken(LoginToken loginToken) {
        strategy.setContext(new SecurityContextImpl(loginToken));
    }


}