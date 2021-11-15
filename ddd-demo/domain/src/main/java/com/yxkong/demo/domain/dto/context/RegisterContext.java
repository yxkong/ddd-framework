package com.yxkong.demo.domain.dto.context;

import com.yxkong.demo.domain.model.user.UserObject;
import lombok.Builder;
import lombok.Getter;

/**
 * 注册上下文
 *
 * @Author: yxkong
 * @Date: 2021/11/15 9:54 PM
 * @version: 1.0
 */
@Builder
@Getter
public class RegisterContext {
    private UserObject userObject;
    private String requestIp;
    private String verifyCode;
    private String pwd;
    private String salt;
    private String proId;
    private String env;
}
