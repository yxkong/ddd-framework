#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.service.impl;

import ${package}.infrastructure.persistence.entity.${appName}.SysConfigDO;
import ${package}.infrastructure.persistence.mapper.${appName}.SysConfigMapper;
import ${package}.infrastructure.service.SysConfigService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @Author: ${author}
 * @Date: 2021/6/10 2:26 下午
 * @version: ${version}
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
    @Cacheable(cacheNames = "c30m",key = "'"+VALUE+"'+${symbol_pound}system+'_'+${symbol_pound}key+':string'",
            cacheManager = "redisCacheManager", unless = "${symbol_pound}result==null")
    public String findValue(String key, String system) {
        final SysConfigDO sysConfigDO = sysConfigMapper.findByKey(key, system);
        if(Objects.nonNull(sysConfigDO)){
            return sysConfigDO.getOptionValue();
        }
        return null;
    }

    @Override
    @Cacheable(cacheNames = "c30m",key = "'"+VALUE+"'+${symbol_pound}system+'_'+${symbol_pound}key+':entity'",
            cacheManager = "redisCacheManager", unless = "${symbol_pound}result==null")
    public SysConfigDO findBy(String key, String system) {
        return sysConfigMapper.findByKey(key,system);
    }

    /**
     * 直接清除auth:sysConfig:string:wk_key 和auth:sysConfig:entity:wk_key 的数据
     * @param sysConfigDO
     * @return
     */
    @Override
    @CacheEvict(cacheNames = "c30m",key = "'"+VALUE+"'+${symbol_pound}system+'_'+${symbol_pound}key",allEntries = true,
            cacheManager = "redisCacheManager",condition="${symbol_pound}p0.id>0")
    public int update(SysConfigDO sysConfigDO){
        return sysConfigMapper.updateByPrimaryKeySelective(sysConfigDO);
    }
}