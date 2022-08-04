<#--<#include "/java_copyright.include"> -->
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${mpackage}.entity;

<#--<#include "/java_imports.include">  -->
<#--import com.snailf.platforms.common.entity.BaseEntity;-->
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
<#if hasDateType>
import java.util.Date;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
/**
<#if table.entityRemark??>
 * @类介绍 ${table.entityRemark}
 </#if>
 * @author 自定义代码生成器
 * @time ${table.createTime}
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${className}Entity implements Serializable  {
  
    
    <#list table.columns as column>
    <#if column.remarks ??>
    /**
     * ${column.remarks}
     */
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#list>
}