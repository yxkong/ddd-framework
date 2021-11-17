package com.yxkong.demo.domain.gateway;

import com.yxkong.common.constant.TenantEnum;
import com.yxkong.demo.domain.dto.context.RegisterContext;
import com.yxkong.demo.domain.model.user.AccountEntity;
import com.yxkong.demo.domain.model.user.UserObject;

/**
 * 注册网关
 *
 * @Author: yxkong
 * @Date: 2021/11/17 3:03 PM
 * @version: 1.0
 */
public interface RegisterGateway {
    /**
     * 是否存在
     * @param userObject
     * @return
     */
    boolean isExist(UserObject userObject);


    /**
     * 注册
     * @param registerContext
     * @return
     */
    AccountEntity register(RegisterContext registerContext);


}
