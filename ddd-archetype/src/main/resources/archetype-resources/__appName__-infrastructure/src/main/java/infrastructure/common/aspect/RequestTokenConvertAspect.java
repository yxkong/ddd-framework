#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.aspect;

import com.yxkong.common.constant.TenantEnum;
import ${package}.infrastructure.common.constant.HeaderConstant;
import ${package}.infrastructure.common.plugin.token.SecurityContextHolder;
import ${package}.infrastructure.common.util.JsonUtils;
import ${package}.infrastructure.common.util.LoginTokenUtil;
import ${package}.infrastructure.common.util.WebUtil;
import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.common.entity.dto.ResultBean;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

@Aspect
@Component
@Order(-999)
public class RequestTokenConvertAspect {

    private static final Logger logger = LoggerFactory.getLogger(RequestTokenConvertAspect.class);
    @Autowired
    private HttpServletRequest httpRequest;
    @Resource(name = "stringRedisTemplate")
    private StringRedisTemplate redisTemplate;

    @Around("execution(* ${groupId}.${appName}..controller.*.*(..))")
    public Object around(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        String className = jp.getTarget().getClass().getName();
        LoginToken loginToken = null;
        try {
            /**  将loginToken 放入到本地线程里  **/
            String loginStr = getLoginInfoString();
            String token = getToken();
            SecurityContextHolder.clearContext();
            if (StringUtils.isNotBlank(loginStr)) {
                loginToken = JsonUtils.fromJson(loginStr, LoginToken.class);
            } else if(Objects.nonNull(token) && !LoginToken.DEFULT_TOKEN.equals(token)) {
                loginStr = redisTemplate.opsForValue().get(LoginTokenUtil.getKey(token));
                loginToken =  JsonUtils.fromJson(loginStr, LoginToken.class);
            }else {
                loginToken = new LoginToken();
                loginToken.setToken(getToken());
                loginToken.setProId(getProId());
                initTenantId(loginToken);
            }

            SecurityContextHolder.setLoginToken(loginToken);
            return jp.proceed(jp.getArgs());
        } catch (Exception e) {
            logger.error("请求类:" + className + ",method=" + methodName + " ,loginToken=" + JsonUtils.toJson(loginToken), e);
            return new ResultBean.Builder<>().fail().message("系统异常，请稍后再试！").build();
        } finally {
            SecurityContextHolder.clearContext();
        }
    }

    /**
     * 获取ProId
     * @return String
     */
    private String getProId() {
        return httpRequest.getHeader(HeaderConstant.PRO_ID);
    }

    private String getToken() {
        return httpRequest.getHeader(HeaderConstant.TOKEN);
    }

    private void initTenantId(LoginToken loginToken) {
        String tenantId = httpRequest.getHeader(HeaderConstant.TENANT_ID);
        if (Objects.isNull(loginToken.getTenantId()) && Objects.nonNull(tenantId) && NumberUtils.isCreatable(tenantId)) {
            loginToken.setTenantId(Integer.parseInt(tenantId));
        }
    }

    /**
     * 获取登录的用户信息（网关已经放入header）
     * @return String
     */
    private String getLoginInfoString() {
        String loginStr = httpRequest.getHeader(HeaderConstant.LOGIN_INFO);
        if (StringUtils.isNotBlank(loginStr)) {
            return getUrlDecoderString(loginStr);
        }
        // 记录没有从网关请求的ip和接口
        logger.warn("没有从网关请求的ip:{},uri:{}", WebUtil.getIpAddress(httpRequest), httpRequest.getRequestURI());
        String token = httpRequest.getHeader(HeaderConstant.TOKEN);
        if (StringUtils.isBlank(token)) {
            return loginStr;
        }
        //TODO 通过用户接口解析token
        //LoginToken loginToken  = userApiService.getLoginTokenByToken(token);
        //return FastJsonUtils.toJson(loginToken);
        return null;
    }

    private String getUrlDecoderString(String str) {
        String result = "";
        if (null == str) {
            return "";
        }
        try {
            result = java.net.URLDecoder.decode(str, StandardCharsets.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            logger.error("解析token异常:", e);
        }
        return result;
    }
}
