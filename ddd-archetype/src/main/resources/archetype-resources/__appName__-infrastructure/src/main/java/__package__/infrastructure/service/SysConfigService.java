#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.service;

import ${package}.infrastructure.persistence.entity.${appName}.SysConfigDO;

/**
 * 配置服务
 *
 * @Author: ${author}
 * @Date: 2021/6/10 2:24 下午
 * @version: ${version}
 */
public interface SysConfigService {
    /**
     * 根据key查配置
     * @param key
     * @param system
     * @return
     */
    String findValue(String key,String system);

    /**
     * 根据key查配置
     * @param key
     * @param system
     * @return
     */
    SysConfigDO findBy(String key, String system);

    /**
     * 更新配置
     * @param sysConfigDO
     * @return
     */
    int update(SysConfigDO sysConfigDO);


}