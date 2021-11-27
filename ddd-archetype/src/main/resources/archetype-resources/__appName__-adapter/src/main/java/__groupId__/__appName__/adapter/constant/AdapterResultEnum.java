#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.adapter.constant;

import ${groupId}.common.exception.BaseResult;

/**
 * <TODO>
 *
 * @Author: ${author}
 * @Date: 2021/11/17 3:28 PM
 * @version: ${version}
 */
public enum AdapterResultEnum implements BaseResult {
    ;

    AdapterResultEnum(String status, String message) {
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