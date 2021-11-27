#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.wrapper;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/11/17 10:47 AM
 * @version: ${version}
 */
public class HttpRequestWrapper {
    public static HttpServletRequest getRequest() {
        return  ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }
}
