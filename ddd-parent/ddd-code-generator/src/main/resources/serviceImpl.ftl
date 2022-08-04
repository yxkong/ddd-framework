<#--<#include "/java_copyright.include"> -->
package ${serviceImplPackage};

<#--<#include "/java_imports.include">  -->
import ${entityFile};
import ${mapperFile};
import ${serviceFile};
import java.util.Map;
import java.util.List;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageHelper;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
/**
 * @author 自定义代码生成器
 * @time ${table.createTime}
 * @version 1.0
 *
 **/
@Service("${firstLowerEntityName}Service")
public class ${entityName}ServiceImpl  implements I${entityName}Service{

	@Resource
	private ${entityName}Mapper ${mapper};

	@Override
	public int insert(${entityName}DO record){
		return ${mapper}.insert${entityName}(record);
	}

	@Override
	public int updateById(${entityName}DO record){
		return ${mapper}.update${entityName}ById(record);
	}

	@Override
	public ${entityName}DO findById(${pkType} id){
		return ${mapper}.findById(id);
	}

	@Override
	List<${entityName}DO> findByIds(${pkType}[] ids){
		return ${mapper}.findByIds(ids);
	}

	@Override
	public List<${entityName}DO> findList(Map<String,Object> params){
		return  ${mapper}.findList(params);
	}

	@Override
	public List<${entityName}DO> findListBy(${entityName}DO record){
		return  ${mapper}.findListBy(record);
	}

	@Override
	public PageInfo<${entityName}DO> findPageInfo(Map<String,Object> params,int pageNum,int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<${entityName}DO> list = ${mapper}.findList(params);
		return new PageInfo<>(list);
	}

	@Override
	public PageInfo<${entityName}DO> findPageInfo(${entityName}DO record,int pageNum,int pageSize){
		PageHelper.startPage(pageNum,pageSize);
		List<${entityName}DO> list = ${mapper}.findListBy(record);
		return new PageInfo<>(list);
	}

	@Override
	public int findListCount(Map<String,Object> params){
		return  ${mapper}.findListCount(params);
	}

	@Override
	public int deleteById(${pkType} id){
		return	${mapper}.deleteById(id);
	}

	@Override
	public int deleteByIds(${pkType}[] ids){
		return	${mapper}.deleteByIds(ids);
	}
}