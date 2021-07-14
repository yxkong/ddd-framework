package com.yxkong.demo.domain.model.authtask;

import com.yxkong.common.annotation.AggregateRoot;
import com.yxkong.demo.domain.model.user.CustomerId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @Author: yxkong
 * @Date: 2021/7/14 10:54 上午
 * @version: 1.0
 */
@AggregateRoot
@Getter
@Setter
@Builder
public class AuthTask {
    private AuthTaskId taskId;
    private final CustomerId customerId;
    private String flowId;
    private Date createTime;

}