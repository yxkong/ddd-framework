package com.yxkong.code.tools;

import com.yxkong.code.utils.DateUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ServiceUtil {

	/**
	 * 构造mapper
	 * 
	 * @return
	 */

	public static Map<String, Object> createMapper(String basePackage, String mPackage, String entityName,
			String tableName, List<Map<String, Object>> columns, Map<String, Object> pkMap) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String pkKey = String.valueOf(pkMap.get("column_name")).toLowerCase();
		String pkVal = ColumnUtil.mysqlColumnToJavaColumn(String.valueOf(pkMap.get("column_name")).toLowerCase());
		String firsetLowerEntityName = ColumnUtil.lowerFirstChar(entityName);
		String nameSpace = basePackage + "." + mPackage + ".mapper." + entityName + "Mapper";
		String resultType = basePackage + "." + mPackage + ".entity." + entityName + "Entity";

		ret.put("nameSpace", nameSpace);
		ret.put("resultType", resultType);
		ret.put("className", entityName);
		ret.put("firsetLowerClassName", firsetLowerEntityName);
		ret.put("tableName", tableName);
		ret.put("basepackage", basePackage);
		ret.put("mpackage", mPackage);
		ret.put("createTime", DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYY_MM_DD_HH_MM_SS_CN));

		return ret;
	}

	public static Map<String, Object> createService(String servicePackage, String mapperPackage, String mpackage, String entityName, String tableName,
			List<Map<String, Object>> columns, Map<String, Object> pkMap) {
		
		Map<String, Object> ret = new HashMap<String, Object>();
		String firsetLowerEntityName = ColumnUtil.lowerFirstChar(entityName);
		String nameSpace = mapperPackage + "." + mpackage + ".mapper." + entityName + "Mapper";
		String resultType = mapperPackage + "." + mpackage + ".entity." + entityName + "Entity";

		ret.put("nameSpace", nameSpace);
		ret.put("resultType", resultType);
		ret.put("className", entityName);
		ret.put("firsetLowerClassName", firsetLowerEntityName);
		ret.put("tableName", tableName);
		ret.put("servicePackage", servicePackage);
		ret.put("mapperPackage", mapperPackage);
		ret.put("mpackage", mpackage);
		ret.put("createTime", DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
		return ret;
	}

}
