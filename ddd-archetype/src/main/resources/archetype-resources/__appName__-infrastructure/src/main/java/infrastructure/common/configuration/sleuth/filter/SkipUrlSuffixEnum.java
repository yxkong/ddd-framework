#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.common.configuration.sleuth.filter;

import java.util.Objects;

/**
 * 不采集过滤枚举
 * @Author: ${author}
 * @Date: 2021/7/27 3:20 下午
 * @version: ${version}
 */
public enum SkipUrlSuffixEnum {
    /**
     * 不采集枚举
     */
    HTM(".htm"), HTML(".html"), CSS(".css"), JS(".js"), PNG(".png"), JPG(".jpg"), GIF(".gif"),
    SWAGGERRESOURCES("/swagger-resources"), APIDOCS("/v2/api-docs");

    // 成员变量
    private String suffix;

    // 构造方法
    private SkipUrlSuffixEnum(String suffix) {
        this.suffix = suffix;
    }

    public String getSuffix() {
        return suffix;
    }

    public static boolean contains(String uri){
         for ( SkipUrlSuffixEnum value: SkipUrlSuffixEnum.values()){
             if (Objects.nonNull(uri) && value.getSuffix().equals(uri)){
                 return true;
             }
         }
         return false;
    }
}
