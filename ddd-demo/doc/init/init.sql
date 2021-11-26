CREATE DATABASE IF NOT EXISTS demo;
use demo;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
     `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识',
     `mobile` varchar(11) NOT NULL DEFAULT '0' COMMENT '用户手机号',
     `pwd` varchar(32) NOT NULL DEFAULT '0' COMMENT '登录密码',
     `salt` varchar(32) NOT NULL DEFAULT '0' COMMENT '密码盐值',
     `status` int(2) DEFAULT '0' COMMENT '账户状态,0启用，1禁用',
     `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
     `proId` varchar(50) DEFAULT NULL COMMENT '注册时渠道号',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `idx_mobile` (`mobile`) USING HASH,
     UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE,
     KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='账户表';

-- ----------------------------
-- Records of t_account
-- ----------------------------
BEGIN;
COMMIT;


-- ----------------------------
-- Table structure for t_account_log
-- ----------------------------
DROP TABLE IF EXISTS `t_account_log`;
CREATE TABLE `t_account_log` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
     `biz_type` int(1) DEFAULT '0' COMMENT '业务类型，0，注册，1,登录，2，改密，3注销，4，绑定账户',
     `env` varchar(10) DEFAULT NULL COMMENT '操作环境，pc/h5/weixin/huawei/xiaomi/oppo/vivo/ios ...',
     `proId` varchar(50) DEFAULT NULL COMMENT '操作渠道',
     `request_ip` varchar(32) DEFAULT NULL COMMENT '注册ip',
     `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='联合账户表';

-- ----------------------------
-- Records of t_account_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_log`;
CREATE TABLE `t_sms_log` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `proId` varchar(32) DEFAULT '' COMMENT '用户来源渠道',
     `verify_code` varchar(6) DEFAULT '' COMMENT '短信验证码',
     `mobile` varchar(11) DEFAULT '' COMMENT '发送验证码手机号',
     `sms_type` int(2) NOT NULL DEFAULT '1' COMMENT '短信类型，1 注册，2登录密码，3重置登录密码',
     `send_status` int(2) DEFAULT '0' COMMENT '验证码发送状态，0初始状态，1成功，-1失败,-2系统异常',
     `use_status` int(2) DEFAULT '0' COMMENT '使用状态，0未使用，1已使用',
     `sms_content` varchar(120) DEFAULT NULL,
     `result` varchar(50) DEFAULT NULL COMMENT '回执',
     `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送验证的时间',
     `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     `request_ip` varchar(32) DEFAULT '127.0.0.1' COMMENT '请求ip',
     `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `IDX_mobile` (`mobile`) USING BTREE,
     KEY `IDX_requestIp` (`request_ip`) USING BTREE,
     KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='发送短信记录';

-- ----------------------------
-- Records of t_sms_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_union_account
-- ----------------------------
DROP TABLE IF EXISTS `t_union_account`;
CREATE TABLE `t_union_account` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
   `open_uuid` varchar(32) NOT NULL COMMENT '开放用户唯一标识',
   `open_platform` varchar(10) DEFAULT 'weixin' COMMENT '开放平台',
   `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='联合账户表';

-- ----------------------------
-- Records of t_union_account
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

CREATE DATABASE IF NOT EXISTS xdemo;
use xdemo;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_account
-- ----------------------------
DROP TABLE IF EXISTS `t_account`;
CREATE TABLE `t_account` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '账户id',
     `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识',
     `mobile` varchar(11) NOT NULL DEFAULT '0' COMMENT '用户手机号',
     `pwd` varchar(32) NOT NULL DEFAULT '0' COMMENT '登录密码',
     `salt` varchar(32) NOT NULL DEFAULT '0' COMMENT '密码盐值',
     `status` int(2) DEFAULT '0' COMMENT '账户状态,0启用，1禁用',
     `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
     `proId` varchar(50) DEFAULT NULL COMMENT '注册时渠道号',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `idx_mobile` (`mobile`) USING HASH,
     UNIQUE KEY `idx_uuid` (`uuid`) USING BTREE,
     KEY `idx_create_time` (`create_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='账户表';

-- ----------------------------
-- Records of t_account
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_account_log
-- ----------------------------
DROP TABLE IF EXISTS `t_account_log`;
CREATE TABLE `t_account_log` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
     `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
     `biz_type` int(10) DEFAULT '0' COMMENT '业务类型，0，注册，1,登录，2，改密，3注销，4，绑定账户',
     `env` varchar(10) DEFAULT NULL COMMENT '操作环境，pc/h5/weixin/huawei/xiaomi/oppo/vivo/ios ...',
     `proId` varchar(50) DEFAULT NULL COMMENT '操作渠道',
     `request_ip` varchar(32) DEFAULT NULL COMMENT '注册ip',
     `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
     `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='联合账户表';

-- ----------------------------
-- Records of t_account_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_sms_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sms_log`;
CREATE TABLE `t_sms_log` (
     `id` bigint(20) NOT NULL AUTO_INCREMENT,
     `proId` varchar(32) DEFAULT '' COMMENT '用户来源渠道',
     `verify_code` varchar(6) DEFAULT '' COMMENT '短信验证码',
     `mobile` varchar(11) DEFAULT '' COMMENT '发送验证码手机号',
     `sms_type` int(2) NOT NULL DEFAULT '1' COMMENT '短信类型，1 注册，2登录密码，3重置登录密码',
     `send_status` int(2) DEFAULT '0' COMMENT '验证码发送状态，0初始状态，1成功，-1失败,-2系统异常',
     `use_status` int(2) DEFAULT '0' COMMENT '使用状态，0未使用，1已使用',
     `sms_content` varchar(120) DEFAULT NULL,
     `result` varchar(50) DEFAULT NULL COMMENT '回执',
     `send_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发送验证的时间',
     `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
     `request_ip` varchar(32) DEFAULT '127.0.0.1' COMMENT '请求ip',
     `tenant_id` int(10) DEFAULT '1001' COMMENT '租户id',
     PRIMARY KEY (`id`) USING BTREE,
     KEY `IDX_mobile` (`mobile`) USING BTREE,
     KEY `IDX_requestIp` (`request_ip`) USING BTREE,
     KEY `idx_tenant_id` (`tenant_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='发送短信记录';

-- ----------------------------
-- Records of t_sms_log
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for t_union_account
-- ----------------------------
DROP TABLE IF EXISTS `t_union_account`;
CREATE TABLE `t_union_account` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
   `open_uuid` varchar(32) NOT NULL COMMENT '开放用户唯一标识',
   `open_platform` varchar(10) DEFAULT 'weixin' COMMENT '开放平台',
   `uuid` bigint(25) NOT NULL COMMENT '用户唯一标识,使用分布式id，相对有序',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `updated_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='联合账户表';

-- ----------------------------
-- Records of t_union_account
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
