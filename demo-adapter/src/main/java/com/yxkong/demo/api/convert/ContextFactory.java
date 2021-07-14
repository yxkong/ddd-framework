package com.yxkong.demo.api.convert;


import com.yxkong.common.entity.common.LoginToken;
import com.yxkong.demo.domain.dto.context.DistributeContext;
import lombok.extern.slf4j.Slf4j;

/**
 * 上下文工厂
 *
 * @Author: yxkong
 * @Date: 2021/6/3 6:51 下午
 * @version: 1.0
 */
@Slf4j
public class ContextFactory {
    /**
     * 获取分发上下文对象
     * @param loginToken 用户登录token
     * @return
     */
    public static DistributeContext distributeContext(LoginToken loginToken){
        return new DistributeContext(loginToken);
    }

}