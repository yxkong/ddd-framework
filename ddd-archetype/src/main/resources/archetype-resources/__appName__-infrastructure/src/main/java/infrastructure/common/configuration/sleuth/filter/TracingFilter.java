#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.sleuth.filter;

import brave.Span;
import brave.Tracing;
import com.yxkong.common.entity.dto.ResultBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 采集输入输出信息到brave.Span
 * @Author: ${author}
 * @Date: 2021/7/27 3:16 下午
 * @version: ${version}
 */
public class TracingFilter extends OncePerRequestFilter {
    private static final Logger logger = LoggerFactory.getLogger(TracingFilter.class);

    public static final Integer PAY_LOAD_MAX_LENGTH = 1024;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest)httpServletRequest;
        String uri = request.getRequestURI();
        //属于跳过资源，直接放行
        if (SkipUrlSuffixEnum.contains(uri)){
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } else {
            final ContentCachingRequestWrapper requestWrapper = new ContentCachingRequestWrapper(httpServletRequest);
            final ContentCachingResponseWrapper responseWrapper = new ContentCachingResponseWrapper(httpServletResponse);
            before(requestWrapper);
            filterChain.doFilter(requestWrapper, responseWrapper);
            after(requestWrapper, responseWrapper);
        }

    }

    /**
     * 执行前设置
     * @param requestWrapper
     */
    private void before(ContentCachingRequestWrapper requestWrapper) {
        if (Objects.nonNull(getSpan())){
            Map<String, String> header = getHeaders((HttpServletRequest)requestWrapper);
            if (!header.isEmpty()){
                header.forEach((k,v) ->{
                    if (!("x-b3-parentspanid".equals(k) || "x-b3-sampled".equals(k) || "x-b3-spanid".equals(k)
                            || "x-b3-traceid".equals(k) || "x-tingyun-id".equals(k))) {
                        putSpan( "request.headers." + k,v);
                    }
                });
            }
            String url = requestWrapper.getQueryString();
            if (url != null && url.length() > 1) {
                putSpan( "request.queryString", url);
            }
        }

    }
    private Span getSpan(){
        Span span = null;
        try {
            span = Tracing.currentTracer().currentSpan();
        } catch (Exception e){
        }
        return span;
    }

    /**
     * 执行完设置
     * @param requestWrapper
     * @param responseWrapper
     */
    private void after(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException{
        String status = null;
        String message = null;

        try {
            status = ResultBean.STATUS.get();
            message = ResultBean.MESSAGE.get();
            ResultBean.remove();
        } catch (Exception e) {
        }
        if (Objects.nonNull(status)){
            putSpan("response.status", status);
        }
        if (Objects.nonNull(message)){
            putSpan("response.message", message);
        }
        String requestPayload = null;
        String responsePayload = null;
        try {
            requestPayload = getPayLoad(requestWrapper.getContentAsByteArray(), requestWrapper.getCharacterEncoding());
            responsePayload = getPayLoad(responseWrapper.getContentAsByteArray(), responseWrapper.getCharacterEncoding());
        } catch (Exception e) {
            logger.warn("getPayLoad异常.", e);
        } finally {
            responseWrapper.copyBodyToResponse();
        }
        putSpan("request.content", requestPayload);
        putSpan("response.content", responsePayload);
        String contentType = requestWrapper.getContentType();
        if (Objects.nonNull(contentType)) {
            putSpan("response.contentType", contentType);
        }
        String characterEncoding = responseWrapper.getCharacterEncoding();
        if (Objects.nonNull(characterEncoding)) {
            putSpan("response.characterEncoding", characterEncoding);
        }

    }
    private String getPayLoad(byte[] buf, String characterEncoding) {
        String payload = "[unknown]";
        if (buf == null || buf.length<=0){
            return payload;
        }
        int length = Math.min(buf.length, PAY_LOAD_MAX_LENGTH);
        try {
            payload = new String(buf, 0, length, characterEncoding);
        } catch (UnsupportedEncodingException ex) {
            logger.warn("getPayLoad 异常.", ex);
        }
        return payload;
    }
    private void putSpan( String key, String value) {
        Span span = getSpan();
        if ( Objects.nonNull(span) && Objects.nonNull(value)) {
            span.tag(key, value);
        }
    }
    public Map<String, String> getHeaders(HttpServletRequest servletRequest) {

        Map<String, String> headers = new HashMap<String, String>();
        for (Enumeration<?> headerNames = servletRequest.getHeaderNames(); headerNames.hasMoreElements();) {
            Object elementKey = headerNames.nextElement();
            String headerName = null;
            if (Objects.nonNull(elementKey)) {
                headerName = (String)elementKey;
            }
            for (Enumeration<?> headerValues = servletRequest.getHeaders(headerName); headerValues.hasMoreElements();) {
                Object element = headerValues.nextElement();
                String headerValue = (String)element;
                if (Objects.nonNull(headerValue)) {
                    headers.put(headerName, headerValue);
                }
            }
        }

        return headers;
    }

}