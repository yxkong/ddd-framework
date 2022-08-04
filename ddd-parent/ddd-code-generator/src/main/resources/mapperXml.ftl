<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${mapperFile}">

	 <resultMap type="${entityFile}" id="BaseResultMap">
    	 ${resultMap}  
    </resultMap>
    <sql id="Base_Column_List">
    	 ${baseColumnList}
    </sql>

	<insert id="insert" useGeneratedKeys="true"  keyProperty="id" parameterType="${entityFile}">
		${insertSql}
	</insert>
	
	<update id="updateById" parameterType="${entityFile}">
		${updateSql}
	</update>
	<select id="findById" resultMap="BaseResultMap" parameterType="java.lang.${pkType}">
		select <include refid="Base_Column_List" /> from ${tableName} t where t.${pkKey}=<#noparse>#{</#noparse>${pkVal}}
	</select>
	<select id="findByIds" resultMap="BaseResultMap" parameterType="java.lang.${pkType}">
		select <include refid="Base_Column_List" /> from ${tableName}
		<where>
			<foreach item="id"  collection="array" open="${pkKey}  in (" separator="," close=")" >
				<#noparse>#{</#noparse>id}
			</foreach>
		</where>
		limit 200
	</select>
	<select id="findList" resultMap="BaseResultMap" parameterType="java.util.Map">
		${listSql}
	</select>
	<select id="findListBy" resultMap="BaseResultMap" parameterType="${entityFile}">
		${listSql}
	</select>
	<select id="findListCount" resultType="java.lang.Integer"  parameterType="java.util.Map">
		${listCountSql}
	</select>
	<delete id="deleteById" parameterType="java.lang.${pkType}">
		delete from ${tableName} where ${pkKey} = <#noparse>#{</#noparse>${pkVal}}
	</delete>
	<delete id="deleteByIds" parameterType="java.lang.${pkType}">
		<!--没有传入数据抛异常，不会全删-->
		<if test="ids != null and ids.length>0">
			delete from ${tableName}
			<where>
				<foreach item="id"  collection="array" open="${pkKey}  in (" separator="," close=")" >
					<#noparse>#{</#noparse>id}
				</foreach>
			</where>
		</if>
	</delete>
</mapper>