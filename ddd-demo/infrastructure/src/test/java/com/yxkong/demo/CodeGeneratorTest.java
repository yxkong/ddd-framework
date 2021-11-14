package com.yxkong.demo;

import com.yxkong.code.config.CodeGenerator;
import com.yxkong.code.config.GeneratorInfo;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2021/10/27 4:41 PM
 * @version: 1.0
 */
public class CodeGeneratorTest {
    public static void main(String[] args) {
        GeneratorInfo generator = new GeneratorInfo.Builder()
                .host("10.255.200.214")
                .port(3306)
                .userName("root")
                .password("jfcf!@#$")
                .dbName("gray_user")
                .tableNames("t_account,t_union_account,t_account_log")
                .groupId("com.yxkong")
                .bizModule("demo")
                .xmlDir("demo")
                .rmPrefix("t_,c_,_tb")
                .build();
        new CodeGenerator(generator).run();
    }
}
