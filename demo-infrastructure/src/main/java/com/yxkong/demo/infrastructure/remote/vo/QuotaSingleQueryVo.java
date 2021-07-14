package com.yxkong.demo.infrastructure.remote.vo;

import lombok.Data;

@Data
public class QuotaSingleQueryVo {

    private Long customerId;

    private Boolean bizFlag = false;

    private Integer quotaType = 0;

    public QuotaSingleQueryVo(Long customerId) {
        this.customerId = customerId;
    }
}
