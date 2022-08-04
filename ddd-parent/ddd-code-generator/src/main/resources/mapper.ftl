<#--<#include "/java_copyright.include"> -->
package ${mapperPackage};

<#--<#include "/java_imports.include">  -->
import ${entityFile};
import java.util.Map;
import java.util.List;
/**
 * @author 自定义代码生成器
 * @time ${table.createTime}
 * @version 1.0
 *
 **/

public interface ${entityName}Mapper  {

	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	int insert(${entityName}DO record);
	/**
	* 通过主键id 更新实体
	* @param record
	* @return 1成功  其它失败
	*/
	int updateById(${entityName}DO record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	${entityName}DO findById(${pkType} id);
	/**
	* 通过主键ids 获取多个实体对象(最多200条)
	* @param ids
	* @return
	*/
	List<${entityName}DO> findById(${pkType}[] ids);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<${entityName}DO>
	 */
	List<${entityName}DO> findList(Map<String,Object> params);
	/**
	* 通过实体查询
	* @param record
	* @return List<${entityName}DO>
	*/
	List<${entityName}DO> findListBy(${entityName}DO record);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int findListCount(Map<String,Object> params);
	/**
	* 通过主键id 删除
	* @param id
	* @return
	*/
	int deleteById(${pkType} id);
	/**
	* 批量删除
	* @param ids
	* @return
	*/
	int deleteByIds(${pkType}[] ids);

}