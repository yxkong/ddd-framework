<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${nameSpace}">

	 <resultMap type="${resultType}" id="BaseResultMap">
    	 ${resultMap}  
    </resultMap>
    <sql id="Base_Column_List">
    	 ${baseColumnList}
    </sql>
    
    <select id="findById" resultMap="BaseResultMap"
		parameterType="java.lang.Long">
		${selectSql}
	</select>

	<delete id="deleteById" parameterType="java.lang.Long">
		${deleteSql}
	</delete>
	
	<insert id="insertSelective" parameterType="${resultType}">
		${insertSql}
	</insert>
	
	<update id="updateByPrimaryKeySelective" parameterType="${resultType}">
		${updateSql}
	</update>
	
	 <select id="getList" resultMap="BaseResultMap"
		parameterType="java.util.Map">
		${listSql}
	</select>
	 <select id="getListCount" resultType="java.lang.Integer"
		parameterType="java.util.Map">
		${listCountSql}
	</select>
</mapper>