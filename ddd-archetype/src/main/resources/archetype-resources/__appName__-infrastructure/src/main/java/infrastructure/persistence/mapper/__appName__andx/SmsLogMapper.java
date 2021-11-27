#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.infrastructure.persistence.mapper.${appName}andx;

import ${package}.infrastructure.persistence.entity.${appName}.SmsLogDO;
import java.util.Map;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
/**
 *
 * @time 2021年11月24日 10:59:44
 * @version ${version}
 *
 **/

public interface SmsLogMapper  {
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
	int insertSelective(SmsLogDO record);
	/**
	 * 通过主键id 获取实体对象
	 * @param id
	 * @return
	 */
	SmsLogDO findById(Long id);

	/**
	 * 根据手机号和指定类型查询验证码
	 * @param mobile
	 * @param smsType
	 * @return
	 */
	SmsLogDO findByMobile(@Param("mobile") String mobile,@Param("smsType") Integer smsType);

	/**
	 * 更新对应类型的验证码
	 * @param mobile
	 * @param smsType
	 * @param verifyCode
	 * @return
	 */
	int updateByMobile(@Param("mobile") String mobile,@Param("smsType") Integer smsType,@Param("verifyCode") String verifyCode);
	/**
	 * 通过主键id 更新实体
	 * @param record
	 * @return 1成功  其它失败
	 */
	int updateByPrimaryKeySelective(SmsLogDO record);
	/**
	 * 通过map参数获取列表
	 * @param params
	 * @return List<SmsLogDO>
	 */
	List<SmsLogDO> getList(Map<String,Object> params);
	/**
	 * 通过map参数获取列表 分页
	 * @param params
	 * @return List<SmsLogDO>
	 */
	List<SmsLogDO> getList(Map<String,Object> params,RowBounds rowBounds);
	/**
	 * 通过map参数获取 总数
	 * @param params
	 * @return int
	 */
	int getListCount(Map<String,Object> params);


}