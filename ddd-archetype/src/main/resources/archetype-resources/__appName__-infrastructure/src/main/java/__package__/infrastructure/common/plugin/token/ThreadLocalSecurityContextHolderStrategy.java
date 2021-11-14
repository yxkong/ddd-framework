#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.plugin.token;

/**
 * @Author: ${author}
 * @Date: 2021/6/3 11:10 上午
 * @version: ${version}
 */
public class ThreadLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy  {
    private static final InheritableThreadLocal<SecurityContext> contextHolder = new InheritableThreadLocal<SecurityContext>();

    @Override
    public void clearContext() {
        contextHolder.remove();
    }

    @Override
    public SecurityContext getContext() {
        SecurityContext ctx = contextHolder.get();

        if (ctx == null) {
            ctx = createEmptyContext();
            contextHolder.set(ctx);
        }

        return ctx;
    }

    @Override
    public void setContext(SecurityContext context) {
        contextHolder.set(context);
    }

    @Override
    public SecurityContext createEmptyContext() {
        return new SecurityContextImpl();
    }
}