#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package};

import ${groupId}.code.config.CodeGenerator;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/10/27 4:41 PM
 * @version: ${version}
 */
public class CodeGeneratorTest {
    public static void main(String[] args) {
        CodeGenerator generator = new CodeGenerator.Builder()
                .ip("192.168.161.219")
                .port("3308")
                .userName("wk_db_rw")
                .password("J@FgbH5_i8hQ")
                .dbName("wk_db_user")
                .tableNames("t_account,t_sysconfig ")
                .servicePackage("${groupId}")
                .mpackage("archetype")
                .mapperPackage("${groupId}")
                .build();
        generator.run();
    }
}
