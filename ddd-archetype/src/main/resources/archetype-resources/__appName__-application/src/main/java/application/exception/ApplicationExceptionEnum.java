#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.application.exception;

import com.yxkong.common.exception.BaseResult;

/**
 * 应用层异常枚举，应用层异常以3开头
 *
 * @Author: ${author}
 * @Date: 2021/11/15 3:05 PM
 * @version: ${version}
 */
public enum ApplicationExceptionEnum implements BaseResult {

    NOT_FOUD_SLIDINGBLOCK_SUPPLIER("3001", "未实现该供应商的滑块功能");

    ApplicationExceptionEnum(String status, String message) {
        this.status = status;
        this.message = message;
    }

    private String status;

    private String message;
    @Override
    public String getStatus() {
        return status;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
