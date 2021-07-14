package com.yxkong.demo.infrastructure.service;


import com.yxkong.demo.infrastructure.persistence.entity.loan.SysConfigDO;

/**
 * 配置服务
 *
 * @Author: yxkong
 * @Date: 2021/6/10 2:24 下午
 * @version: 1.0
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