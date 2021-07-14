package com.yxkong.demo.infrastructure.persistence.mapper.loanandx;

import com.yxkong.demo.infrastructure.common.plugin.mybatis.TableSeg;
import com.yxkong.demo.infrastructure.persistence.entity.loanandx.RiskApplyDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 *
 * @time 2021年06月04日 17:40:19
 * @version 1.0
 *
 **/
@TableSeg(tableName = "t_risk_apply", nums = 10, shardBy = "customerId")
public interface RiskApplyMapper {
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
	int insertSelective(RiskApplyDO record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	RiskApplyDO selectById(@Param("customerId") Long customerId, @Param("id") Long id);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(RiskApplyDO record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<RiskApplyDO>
	 */
	List<RiskApplyDO> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<RiskApplyDO>
	 */
	List<RiskApplyDO> getList(Map<String,Object> params,RowBounds rowBounds);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);

	RiskApplyDO selectByFlowId(@Param("customerId") Long customerId, @Param("flowId") String flowId);

    RiskApplyDO selectLatest(@Param("customerId") Long customerId);
}
