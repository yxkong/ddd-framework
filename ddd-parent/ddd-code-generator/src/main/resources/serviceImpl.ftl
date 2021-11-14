<#--<#include "/java_copyright.include"> -->
package ${servicePackage}.${mpackage}.service.impl;

<#--<#include "/java_imports.include">  -->
import ${mapperPackage}.${mpackage}.entity.${className}DO;
import ${servicePackage}.${mpackage}.service.${className}Service;
import ${mapperPackage}.${mpackage}.mapper.${className}Mapper;
import java.util.Map;
import java.util.List;
import com.onecard.core.model.PageInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time ${createTime}
 * @version 1.0
 *
 **/
@Service("${firsetLowerClassName}Service")
public class ${className}ServiceImpl  implements ${className}Service{

	@Autowired
	private ${className}Mapper ${firsetLowerClassName}Mapper;
  	/**
	 * 通过主键id 删除
	 * @param id
	 * @return
	 */
	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int deleteByPrimaryKey(Long id){
		return	${firsetLowerClassName}Mapper.deleteByPrimaryKey(id);
	}
	/**
	 * 插入实体
	 * @param record
	 * @return
	 */
	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int insertSelective(${className}DO record){
		return ${firsetLowerClassName}Mapper.insertSelective(record);
	}
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	public ${className}DO selectByPrimaryKey(Long id){
		return ${firsetLowerClassName}Mapper.selectByPrimaryKey(id);
	}
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	//@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = RuntimeException.class)
	public int updateByPrimaryKeySelective(${className}DO record){
		return ${firsetLowerClassName}Mapper.updateByPrimaryKeySelective(record);
	}
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<${className}DO>
	 */
	public List<${className}DO> getList(Map<String,Object> params){
		return  ${firsetLowerClassName}Mapper.getList(params);
	}
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return PageInfo<${className}DO>
	 */
	public PageInfo<${className}DO> getList(PageInfo<${className}Entity> pageInfo,Map<String,Object> params){
		List<${className}Entity> list = ${firsetLowerClassName}Mapper.getList(params,
				new RowBounds(pageInfo.getStart(), pageInfo.getPageSize()));
		Integer total = ${firsetLowerClassName}Mapper.getListCount(params);
		pageInfo.setRows(list);
		pageInfo.setTotal(total);
		return pageInfo;
	}
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	public int getListCount(Map<String,Object> params){
		return  ${firsetLowerClassName}Mapper.getListCount(params);
	}

}