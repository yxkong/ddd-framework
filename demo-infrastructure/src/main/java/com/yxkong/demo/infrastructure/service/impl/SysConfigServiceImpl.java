package com.yxkong.demo.infrastructure.service.impl;


import com.yxkong.demo.infrastructure.persistence.entity.loan.SysConfigDO;
import com.yxkong.demo.infrastructure.persistence.mapper.loan.SysConfigMapper;
import com.yxkong.demo.infrastructure.service.SysConfigService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: yxkong
 * @Date: 2021/6/10 2:26 下午
 * @version: 1.0
 */
@Service
public class SysConfigServiceImpl implements SysConfigService {
    private static final String VALUE = "auth:sysConfig:";
    @Resource
    private SysConfigMapper sysConfigMapper;

    /**
     * auth:sysConfig:wk:key:string
     * @param key
     * @param system
     * @return
     */
    @Override
    @Cacheable(cacheNames = "c30m",key = "'"+VALUE+"'+#system+'_'+#key+':string'",
            cacheManager = "redisCacheManager", unless = "#result==null")
    public String findValue(String key, String system) {
        final SysConfigDO sysConfigDO = sysConfigMapper.findByKey(key, system);
        if(Objects.nonNull(sysConfigDO)){
            return sysConfigDO.getOptionValue();
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "c30m",key = "'"+VALUE+"'+#system+'_'+#key+':entity'",
            cacheManager = "redisCacheManager", unless = "#result==null")
    public SysConfigDO findBy(String key, String system) {
        return sysConfigMapper.findByKey(key,system);
    }

    /**
     * 直接清除auth:sysConfig:string:wk_key 和auth:sysConfig:entity:wk_key 的数据
     * @param sysConfigDO
     * @return
     */
    @Override
    @CacheEvict(cacheNames = "c30m",key = "'"+VALUE+"'+#system+'_'+#key",allEntries = true,
            cacheManager = "redisCacheManager",condition="#p0.id>0")
    public int update(SysConfigDO sysConfigDO){
        return sysConfigMapper.updateByPrimaryKeySelective(sysConfigDO);
    }
}