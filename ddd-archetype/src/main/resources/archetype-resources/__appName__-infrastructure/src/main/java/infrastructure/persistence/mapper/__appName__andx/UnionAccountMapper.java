#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.persistence.mapper.${appName}andx;

import ${package}.infrastructure.persistence.entity.${appName}.UnionAccountDO;
import java.util.Map;
import java.util.List;
import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2021年11月11日 17:25:39
 * @version ${version}
 *
 **/

public interface UnionAccountMapper  {
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
	int insertSelective(UnionAccountDO record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	UnionAccountDO findById(Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(UnionAccountDO record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<UnionAccountDO>
	 */
	List<UnionAccountDO> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<UnionAccountDO>
	 */
	List<UnionAccountDO> getList(Map<String,Object> params,RowBounds rowBounds);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

}