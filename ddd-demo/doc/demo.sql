CREATE TABLE `t_account` (
 `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
 `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
 `mobile` varchar(50) NOT NULL DEFAULT '0' COMMENT '用户手机号',
 `pwd` varchar(32) NOT NULL DEFAULT '0' COMMENT '登录密码',
 `salt` varchar(32) NOT NULL DEFAULT '0' COMMENT '密码盐值',
 `status` int(2) DEFAULT '0' COMMENT '账户状态,0启用，1禁用',
 `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
 `proId` varchar(50) DEFAULT NULL COMMENT '注册时渠道号',
 `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
 `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
 PRIMARY KEY (`id`) USING BTREE,
 UNIQUE KEY `idx_mobile` (`mobile`) USING HASH,
 UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE,
 KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='账户表';

create table `t_union_account`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `open_uuid` varchar(32) NOT NULL  COMMENT '开放用户唯一标识',
    `open_platform` varchar (10) DEFAULT 'weixin' COMMENT '开放平台',
    `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
    `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='联合账户表';

create table `t_account_log`(
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
    `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
    `biz_type` int (10) DEFAULT '0' COMMENT '业务类型，0，注册，1,登录，2，改密，3注销，4，绑定账户',
    `env` varchar(10) DEFAULT NULL COMMENT '操作环境，pc/h5/weixin/huawei/xiaomi/oppo/vivo/ios ...',
    `proId` varchar(50) DEFAULT NULL COMMENT '操作渠道',
    `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
    `create_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='联合账户表';
