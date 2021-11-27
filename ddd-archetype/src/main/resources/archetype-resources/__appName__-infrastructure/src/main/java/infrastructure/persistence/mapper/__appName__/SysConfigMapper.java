#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.persistence.mapper.${appName};

import ${package}.infrastructure.persistence.entity.${appName}.SysConfigDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 *
 * @time 2021年06月04日 17:44:51
 * @version ${version}
 *
 **/

public interface SysConfigMapper {
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
	int insertSelective(SysConfigDO record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	SysConfigDO findById(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(SysConfigDO record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<SysconfigDO>
	 */
	List<SysConfigDO> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<SysconfigDO>
	 */
	List<SysConfigDO> getList(Map<String,Object> params,RowBounds rowBounds);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

	SysConfigDO findByKey(@Param("optionKey") String key, @Param("system") String system);
}