package com.yxkong.demo.infrastructure.rpc.internal.vo;

import lombok.Data;

@Data
public class CreditRecordQueryVo {

    private String certId;
    private String mobile;
    private Long customerId;
    private String userName;
    // 模式
    private String model = "fr";
    private String orderNo;
    // 发起节点,auth:授信;金融云:finance;金融云发起转授信:financeToAuth
    private String node = "auth";
    private String proId;
}
