package com.yxkong.demo.infrastructure.rpc.internal.dto;

import lombok.Data;

/**
 * @Author: yxkong
 * @Date: 2021/6/11 4:40 下午
 * @version: 1.0
 */
@Data
public class CardAuthDTO {

    private Integer status;
    private String failReason;
}