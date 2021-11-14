#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.domain.model.user;

import lombok.Builder;
import lombok.Getter;

/**
 * @Author: ${author}
 * @date 2021-06-11 16:42
 */
@Getter
@Builder
// @DomainValueObject
public class CustomerScore {
    private final String gradeLevel;
}
