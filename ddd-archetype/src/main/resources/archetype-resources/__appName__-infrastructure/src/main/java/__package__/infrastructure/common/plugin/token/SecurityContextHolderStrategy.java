#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
/**
 * @Author: ${author}
 * @Date: 2021/6/3 11:09 上午
 * @version: ${version}
 */
package ${package}.infrastructure.common.plugin.token;

/**
 * 〈〉
 *
 * @Author: ${author}
 * @create 2021/6/3
 * @since ${version}.0
 */
public interface SecurityContextHolderStrategy {

    /**
     * Clears the current context.
     */
    void clearContext();

    /**
     * Obtains the current context.
     *
     * @return a context (never <code>null</code> - create a default implementation if necessary)
     */
    SecurityContext getContext();

    /**
     * Sets the current context.
     *
     * @param context to the new argument (should never be <code>null</code>, although implementations must check if
     *        <code>null</code> has been passed and throw an <code>IllegalArgumentException</code> in such cases)
     */
    void setContext(SecurityContext context);

    /**
     * Creates a new, empty context implementation, for use by <tt>SecurityContextRepository</tt> implementations,
     * when creating a new context for the first time.
     *
     * @return the empty context.
     */
    SecurityContext createEmptyContext();
}
