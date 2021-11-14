package com.yxkong.code;

import com.yxkong.code.config.CodeGenerator;
import com.yxkong.code.config.GeneratorInfo;

/**
 * @Author: yxkong
 * @version: 1.0
 */
public class CodeGeneratorTest {

    public static void main(String[] args) {
        GeneratorInfo generator = new GeneratorInfo.Builder()
                .host("127.0.0.1")
                .port(3306)
                .userName("root")
                .password("123456")
                .dbName("gray_user")
                .tableNames("t_account")
                .groupId("com.yxkong")
                .bizModule("demo")
                .xmlDir("user")
                .rmPrefix("t_,c_,_tb")
                .build();
        new CodeGenerator(generator).run();
    }
}
