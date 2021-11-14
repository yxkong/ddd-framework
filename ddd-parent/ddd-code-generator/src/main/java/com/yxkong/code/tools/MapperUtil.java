package com.yxkong.code.tools;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperUtil {

	/**
	 * 构造mapper
	 * 
	 * @return
	 */

	public static Map<String, Object> createMapper(String persistencePackage,String bizModule, String entityName,
			String tableName, List<Map<String, Object>> columns, Map<String, Object> pkMap) {
		Map<String, Object> ret = new HashMap<String, Object>();
		String pkKey = String.valueOf(pkMap.get("column_name")).toLowerCase();
		String pkVal = ColumnUtil.mysqlColumnToJavaColumn(String.valueOf(pkMap.get("column_name")).toLowerCase());
		String firsetLowerEntityName = ColumnUtil.lowerFirstChar(entityName);
		String nameSpace = persistencePackage  + ".mapper."+bizModule+"." + entityName + "Mapper";
		String resultType = persistencePackage  + ".entity."+bizModule+"." + entityName + "DO";
		String resultMap = creatResultStr(columns);
		String baseColumnList = creatbaseColumnListStr(columns, entityName);
		// 创建查询sql
		String selectSql = createSelectSql(tableName, firsetLowerEntityName, pkKey, pkVal);
		// 创建删除sql
		String deleteSql = createDeleteSql(tableName, firsetLowerEntityName, pkKey, pkVal);
		// 创建插入sql
		String insertSql = createInsertSql(columns, tableName);
		// 创建更新sql
		String updateSql = createUpdateSql(columns, tableName, pkKey, pkVal);
		// 创建列表sql
		String listSql = createListSql(columns, tableName, firsetLowerEntityName);
		// 创建列表sql
		String listCountSql = createListCountSql(columns, tableName, firsetLowerEntityName, pkKey);
		ret.put("nameSpace", nameSpace);
		ret.put("resultType", resultType);
		ret.put("entityName", entityName);
		ret.put("firsetLowerEntityName", firsetLowerEntityName);
		ret.put("resultMap", resultMap);
		ret.put("tableName", tableName);
		ret.put("baseColumnList", baseColumnList);
		ret.put("selectSql", selectSql);
		ret.put("deleteSql", deleteSql);
		ret.put("insertSql", insertSql);
		ret.put("updateSql", updateSql);
		ret.put("listSql", listSql);
		ret.put("listCountSql", listCountSql);
		return ret;
	}

	public static String creatResultStr(List<Map<String, Object>> columns) {
		StringBuffer sbBuffer = new StringBuffer();
		int len = columns.size();
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			if (i == 0) {
				sbBuffer.append("<id column=\"" + column.get("columnNameLower") + "\" property=\""
						+ column.get("columnNameLower") + "\" /> \r\n");
				// + column.get("columnNameLower") + "\" jdbcType=\"" +
				// column.get("mysqlType") + "\" /> \r\n");
			} else {
				sbBuffer.append("\t\t  <id column=\"" + column.get("columnNameLower") + "\" property=\""
						+ column.get("columnNameLower") + "\" /> \r\n");
				// + column.get("columnNameLower") + "\" jdbcType=\"" +
				// column.get("mysqlType") + "\" /> \r\n");
			}
		}

		return sbBuffer.toString();
	}

	public static String creatbaseColumnListStr(List<Map<String, Object>> columns, String entityName) {
		StringBuffer sbBuffer = new StringBuffer();
		int len = columns.size();
		for (int i = 0; i < len; i++) {

			Map<String, Object> column = columns.get(i);
			if (i == 0) {
				sbBuffer.append( "t." + column.get("column") + " as "
						+ column.get("columnNameLower"));
			} else {
				sbBuffer.append(" ,\r\n \t\t ");
				sbBuffer.append( "t." + column.get("column") + " as "
						+ column.get("columnNameLower"));
			}
		}

		return sbBuffer.toString();
	}

	public static String createSelectSql(String tableName, String firsetLowerEntityName, String pkKey, String pkVal) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" select ");
		sbBuffer.append("\r\n \t\t  <include refid=\"Base_Column_List\" />");
		sbBuffer.append("\r\n \t\t  from " + tableName + " t" );
		sbBuffer.append("\r\n \t\t  where t." + pkKey + " = #{ " + pkVal + " }");

		return sbBuffer.toString();
	}

	public static String createDeleteSql(String tableName, String firsetLowerEntityName, String pkKey, String pkVal) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" delete ");
		sbBuffer.append("\r\n \t\t  from " + tableName);
		sbBuffer.append("\r\n \t\t  where " + pkKey + " = #{ " + pkVal + " }");

		return sbBuffer.toString();
	}

	public static String createInsertSql(List<Map<String, Object>> columns, String tableName) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" insert into  " + tableName);
		sbBuffer.append("\r\n \t\t  <trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> ");
		int len = columns.size();
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			sbBuffer.append("\r\n \t\t\t <if test=\"" + column.get("columnNameLower") + " != null\">");
			sbBuffer.append("\r\n \t\t\t\t " + column.get("column") + ",");
			sbBuffer.append("\r\n \t\t\t  </if>");
		}
		sbBuffer.append("\r\n \t\t  </trim>");

		sbBuffer.append("\r\n \t\t  <trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			sbBuffer.append("\r\n \t\t\t <if test=\"" + column.get("columnNameLower") + " != null\">");
			sbBuffer.append("\r\n \t\t\t\t #{" + column.get("columnNameLower")
			// sbBuffer.append("\r\n \t\t\t\t #{" +
			// column.get("columnNameLower") + ",jdbcType=" +
			// column.get("mysqlType")
					+ "},");
			sbBuffer.append("\r\n \t\t\t  </if>");
		}
		sbBuffer.append("\r\n \t\t  </trim>");
		return sbBuffer.toString();
	}

	public static String createUpdateSql(List<Map<String, Object>> columns, String tableName, String pkKey, String pkVal) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" update   " + tableName);
		sbBuffer.append("\r\n \t\t <set>");
		int len = columns.size();
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			if (column.get("column").equals(pkKey) == false) {
				sbBuffer.append("\r\n \t\t\t <if test=\"" + column.get("columnNameLower") + " != null\">");
				sbBuffer.append("\r\n \t\t\t\t " + column.get("column") + " = " + "#{" + column.get("columnNameLower")
						+ "},");
				sbBuffer.append("\r\n \t\t\t  </if>");
			}
		}
		sbBuffer.append("\r\n \t\t </set>");
		sbBuffer.append("\r\n     where " + pkKey + " = #{" + pkVal + "}");
		return sbBuffer.toString();
	}

	public static String createListSql(List<Map<String, Object>> columns, String tableName, String firsetLowerEntityName) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" select ");
		sbBuffer.append("\r\n \t\t  <include refid=\"Base_Column_List\" />");
		sbBuffer.append("\r\n \t\t  from " + tableName + " t" );
		sbBuffer.append("\r\n \t\t  where 1=1 ");
		int len = columns.size();
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			sbBuffer.append("\r\n \t\t <if test=\"" + column.get("columnNameLower") + " != null\">");
			sbBuffer.append("\r\n \t\t\t and t." + column.get("column") + " = " + "#{"
					+ column.get("columnNameLower") + "}");
			sbBuffer.append("\r\n \t\t  </if>");
		}

		return sbBuffer.toString();
	}

	public static String createListCountSql(List<Map<String, Object>> columns, String tableName,
			String firsetLowerEntityName, String pkKey) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append(" select ");
		sbBuffer.append("\r\n \t\t  count(t." + pkKey + ")");
		sbBuffer.append("\r\n \t\t  from " + tableName + " t" );
		sbBuffer.append("\r\n \t\t  where 1=1 ");
		int len = columns.size();
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			sbBuffer.append("\r\n \t\t <if test=\"" + column.get("columnNameLower") + " != null\">");
			sbBuffer.append("\r\n \t\t\t and t." + column.get("column") + " = " + "#{"
					+ column.get("columnNameLower") + "}");
			sbBuffer.append("\r\n \t\t  </if>");
		}

		return sbBuffer.toString();
	}
}
