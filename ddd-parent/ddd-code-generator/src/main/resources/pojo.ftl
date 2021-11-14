<#--<#include "/java_copyright.include"> -->
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${mpackage}.entity;

<#--<#include "/java_imports.include">  -->
<#--import com.snailf.platforms.common.entity.BaseEntity;-->
 import java.io.Serializable; 
<#if import>
import java.util.Date;
</#if>
<#if hasBigDecimal>
import java.math.BigDecimal;
</#if>
/**
<#if table.entityRemark??>
 *
 * @类介绍 ${table.entityRemark}
 </#if>
 * @time ${table.createTime}
 * @version 1.0
 *
 **/

@SuppressWarnings("serial")
public class ${className}DO implements Serializable  {
  
    
    <#list table.columns as column>
    <#if column.remarks ??>
    /**
     * ${column.remarks}
     */
    </#if>
    private ${column.simpleJavaType} ${column.columnNameLower};
    </#list>

<@generateJavaColumns/>

<#macro generateJavaColumns>
    <#list table.columns as column>
        <#if column.isDateTimeColumn>
    public String get${column.columnName}String() {
        return DateConvertUtils.format(get${column.columnName}(), FORMAT_${column.constantName});
    }
    public void set${column.columnName}String(String ${column.columnNameLower}) {
        set${column.columnName}(DateConvertUtils.parse(${column.columnNameLower}, FORMAT_${column.constantName},${column.simpleJavaType}.class));
    }
        </#if>  
       <#if (column.remarks) ??>
	/**
	 * 设置${column.remarks}
	 */
	    </#if>
    public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
     <#if (column.remarks) ??>
    /**
     * 获取${column.remarks}
     */
    </#if>
    public ${column.simpleJavaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }
    </#list>
</#macro>
}