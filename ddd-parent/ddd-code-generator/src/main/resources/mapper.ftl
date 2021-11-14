<#--<#include "/java_copyright.include"> -->
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${persistencePackage}.${bizModule}.mapper;

<#--<#include "/java_imports.include">  -->
import ${persistencePackage}.entity.${bizModule}.${className}DO;
import java.util.Map;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time ${table.createTime}
 * @version 1.0
 *
 **/

public interface ${className}Mapper  {
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteById(Long id);
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	int insertSelective(${className}DO record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	${className}DO findById(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(${className}DO record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<${className}DO>
	 */
	List<${className}DO> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<${className}DO>
	 */
	List<${className}DO> getList(Map<String,Object> params,RowBounds rowBounds);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}