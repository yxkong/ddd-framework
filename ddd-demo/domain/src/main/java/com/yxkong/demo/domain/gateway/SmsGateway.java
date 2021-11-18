package com.yxkong.demo.domain.gateway;

import com.yxkong.common.entity.dto.ResultBean;
import com.yxkong.demo.domain.model.user.UserObject;

/**
 * 短信网关
 * @Author: yxkong
 * @Date: 2021/11/15 3:16 PM
 * @version: 1.0
 */
public interface SmsGateway {

    /**
     * 发送短信验证码
     * @param user 用户值对象
     * @param requestIp 请求ip
     * @return
     */
    ResultBean send(UserObject user,String requestIp);

    /**
     * 发送前校验
     * @param user
     * @param requestIp
     * @return
     */
    Boolean validate(UserObject user, String requestIp);
}
