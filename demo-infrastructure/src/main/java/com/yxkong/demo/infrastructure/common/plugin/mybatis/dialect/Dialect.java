package com.yxkong.demo.infrastructure.common.plugin.mybatis.dialect;

/**
 * @Author: yxkong
 * @Date: 2021/6/3 5:15 下午
 * @version: 1.0
 */
public abstract class Dialect {
    public static enum Type {
        MYSQL, ORACLE
    }

    public abstract String getLimitString(String sql, int skipResults, int maxResults);

}