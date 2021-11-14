<#--<#include "/java_copyright.include"> -->
package ${servicePackage}.${mpackage}.service;

<#--<#include "/java_imports.include">  -->
import ${mapperPackage}.${mpackage}.entity.${className}Entity;
import java.util.Map;
import java.util.List;
import com.onecard.core.model.PageInfo;
/**
 *
 * @time ${createTime}
 * @version 1.0
 *
 **/

public interface ${className}Service  {
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(Long id);
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	int insertSelective(${className}Entity record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	${className}Entity selectByPrimaryKey(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(${className}Entity record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<${className}Entity>
	 */
	List<${className}Entity> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<${className}Entity>
	 */
	PageInfo<${className}Entity> getList(PageInfo<${className}Entity> pageInfo,Map<String,Object> params);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}