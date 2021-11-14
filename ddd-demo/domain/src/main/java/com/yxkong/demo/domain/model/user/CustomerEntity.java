package com.yxkong.demo.domain.model.user;

import com.yxkong.common.annotation.AggregateRoot;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户领域对象
 *
 * @Author: yxkong
 * @Date: 2021/5/31 6:05 下午
 * @version: 1.0
 */
@Getter
@Setter
@Builder
@AggregateRoot
public class CustomerEntity {

    private CustomerId customerId;
    /**
     * 用户身份证号（严格意义上此为业务唯一主键，但在我们系统，我们认为id才是唯一）
     */
    private String certId;
    /**
     * 用户姓名
     */
    private String realName;
    /**
     * 用户实名渠道
     */
    private String proId;
    private String sex;
    private String birthday;
    private CustomerScore score;
}