<#--<#include "/java_copyright.include"> -->
package ${servicePackage};

<#--<#include "/java_imports.include">  -->
import ${entityFile};
import java.util.Map;
import java.util.List;
import com.github.pagehelper.PageInfo;
/**
 * @author 自定义代码生成器
 * @time ${table.createTime}
 * @version 1.0
 *
 **/

public interface I${entityName}Service  {

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
	List<${entityName}DO> findByIds(${pkType}[] ids);
	/**
	* 通过map参数获取列表
	* @param params 查询参数
	* @return List<${entityName}DO>
	*/
	List<${entityName}DO> findList(Map<String,Object> params);
	/**
	* 通过map参数获取列表
	* @param record 查询参数
	* @return List<${entityName}DO>
	*/
	List<${entityName}DO> findListBy(${entityName}DO record);
	/**
	 * 通过map参数获取列表 分页
	 * @param params 查询参数
	 * @param pageNum 页数
	 * @param pageSize 每页大小
	 * @return PageInfo<${entityName}DO>
	 */
	PageInfo<${entityName}DO> findPageInfo(Map<String,Object> params,int pageNum,int pageSize);
	/**
	* 通过实体参数获取列表分页
	* @param record
	* @param pageNum 页数
	* @param pageSize 每页大小
	* @return PageInfo<${entityName}DO>
	*/
	PageInfo<${entityName}DO> findPageInfo(${entityName}DO record,int pageNum,int pageSize);
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