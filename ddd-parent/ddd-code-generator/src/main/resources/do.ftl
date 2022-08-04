<#--<#include "/java_copyright.include"> -->
package ${entityPackage};
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.yxkong.common.entity.BaseEntity;
<#if hasDateType>
import java.time.LocalDateTime;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
/**
<#if table.entityRemark??>
 *
 * @author 自定义代码生成器
 * @类介绍 ${table.entityRemark}
 </#if>
 * @time ${table.createTime}
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${entityName}DO extends BaseEntity   {
<#list table.columns as column>
    <#if column.remarks ??>
    /**
     * ${column.remarks}
     */
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
</#list>
}