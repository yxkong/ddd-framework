#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${groupId}.${appName};

import com.yxkong.code.config.CodeGenerator;
import  com.yxkong.code.config.GeneratorInfo;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/10/27 4:41 PM
 * @version: ${version}
 */
public class CodeGeneratorTest {
    public static void main(String[] args) {
        GeneratorInfo generator = new GeneratorInfo.Builder()
                .host("10.255.200.214")
                .port(3306)
                .userName("root")
                .password("jfcf!@${symbol_pound}${symbol_dollar}")
                .dbName("gray_user")
                .tableNames("t_account,t_union_account,t_account_log")
                .groupId("${groupId}")
                .bizModule("${appName}")
                .xmlDir("${appName}")
                .rmPrefix("t_,c_,_tb")
                .build();
        new CodeGenerator(generator).run();
    }
}
