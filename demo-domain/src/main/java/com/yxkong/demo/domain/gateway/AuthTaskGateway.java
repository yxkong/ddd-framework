/**
 * @Author: yxkong
 * @Date: 2021/7/14 10:53 上午
 * @version: 1.0
 */
package com.yxkong.demo.domain.gateway;

import com.yxkong.demo.domain.model.authtask.AuthTask;
import com.yxkong.demo.domain.model.user.CustomerId;

/**
 * 〈〉
 *
 * @author yxkong
 * @create 2021/7/14
 * @since 1.0.0
 */
public interface AuthTaskGateway {
    /**
     * 查询最新一条任务
     * @param customerId
     * @return
     */
    AuthTask queryLatest(CustomerId customerId);
}
