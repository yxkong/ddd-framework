package com.yxkong.demo.infrastructure.common.plugin.token;

/**
 * @Author: yxkong
 * @Date: 2021/6/3 11:10 上午
 * @version: 1.0
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