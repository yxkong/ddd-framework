/**
 * 配置网关
 *
 * @Author: yxkong
 * @Date: 2021/6/3 7:17 下午
 * @version: 1.0
 */
package com.yxkong.demo.domain.gateway;

/**
 * 〈配置网关〉
 *
 * @author yxkong
 * @create 2021/6/3
 * @since 1.0.0
 */
public interface ConfigGateway {
    /**
     * 根据key查询配置
     * ps: 按理说不应该关注是哪个key，为了通用...
     * @param key
     * @return
     */
    String query(String key, String defaultValue);
}
