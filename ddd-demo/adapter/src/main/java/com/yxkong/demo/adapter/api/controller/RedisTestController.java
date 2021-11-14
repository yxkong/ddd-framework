package com.yxkong.demo.adapter.api.controller;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.infrastructure.common.util.CacheUtils;
import com.yxkong.demo.infrastructure.common.util.CommonUtil;
import io.swagger.annotations.ApiOperation;
import org.redisson.api.RLock;
import org.springframework.web.bind.annotation.*;

/**
 * @author navyzhung
 * @date 2021/6/22-16:29
 */
@RestController
@RequestMapping(value = "/test/redis")
public class RedisTestController {

    @ApiOperation(value = "redis设置")
    @PostMapping(value = "/set")
    public ResultBean<?> set(String key, String value) {
        try {
            CacheUtils.set(key, value);
            return new ResultBean.Builder<>().success().build();
        } catch (Exception e){
            e.printStackTrace();
            return new ResultBean.Builder<>().fail(e.getMessage()).build();
        }
    }

    @ApiOperation(value = "redis删除")
    @DeleteMapping(value = "/del")
    public ResultBean<Object> del(String key) {
        CacheUtils.del(key);
        return new ResultBean.Builder<>().success().build();
    }

    @ApiOperation(value = "redis获取")
    @GetMapping(value = "/get")
    public ResultBean<Object> get(String key) {
        Object data = CacheUtils.get(key);
        return new ResultBean.Builder<>().success(data).build();
    }

    /**
     * 测试redisson
     */
    @GetMapping("/redissonTest")
    public void redissonTest(String key) throws InterruptedException {
        RLock lock = CommonUtil.REDISSON_CLIENT.getLock(key);
        lock.lock();
        Thread.sleep(4000);
        System.out.println(lock.isLocked());
        System.out.println(lock.remainTimeToLive());
        lock.unlock();
    }
}
