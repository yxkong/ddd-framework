package com.yxkong.code.config;

import com.yxkong.code.tools.ColumnUtil;
import com.yxkong.code.tools.DBMSMetaUtil;
import com.yxkong.code.tools.FreemarkerUtils;
import com.yxkong.code.tools.PathAndPackageInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.*;

public class CodeGenerator {

	private  GeneratorInfo generatorInfo;
	private PathAndPackageInfo pathInfo;

    public CodeGenerator(GeneratorInfo info){
		if (info == null){
			throw new RuntimeException("generatorInfo 不能为空");
		}
		this.generatorInfo = info;
		this.pathInfo = new PathAndPackageInfo(generatorInfo);
	}
	public void run() {
		try {
			DBMSMetaUtil dbmsMetaUtil = new DBMSMetaUtil(generatorInfo);
			List<String> tablels = new ArrayList<>();
			if (generatorInfo.getTableNames().contains("*")){
				List<Map<String, Object>> maps = dbmsMetaUtil.listTables();
				maps.forEach(s-> System.out.println(s));
			} else {
				tablels = Arrays.asList(generatorInfo.getTableNames().split(","));
			}
			this.mkdir();
			FreemarkerUtils hf = new FreemarkerUtils();
			hf.init();
			for (String tableName:tablels){
				//查询指定的表的元信息
				Map<String, Object> tableMap = dbmsMetaUtil.selectTable(tableName);
				List<Map<String, Object>> columns = dbmsMetaUtil.listColumns(tableName);
				List<Map<String, Object>> pkColumns = dbmsMetaUtil.listPkColumn( tableName);

				String entityName = ColumnUtil.getEntityName(tableName,generatorInfo.getRmPrefix());
				String entityRemark = String.valueOf(tableMap.get("remarks"));

				columns = ColumnUtil.mysqlColumnsToJavaColumns(columns);
				Map<String, Object> pkMap = pkColumns.get(0);
				Optional<Map<String, Object>> pk = columns.stream().filter(s -> s.get("column").equals(pkMap.get("column_name"))).findFirst();
				Map<String,Object> map = createMap(columns,tableName,entityName,entityRemark,pk.get());

				try {
					// 生成实体DO对象和mapper
					Pair<String, String> pFile = pathInfo.getPersistenceAbsoluteFile(entityName);
					process(hf,map,pFile.getKey(),"do.ftl");
					process(hf,map,pFile.getValue(),"mapper.ftl");
					//生成xml
					process(hf,map,pathInfo.getXmlFile(entityName),"mapperXml.ftl");
					Pair<String, String> sFile = pathInfo.getServiceAbsoluteFile(entityName);
					process(hf,map,sFile.getKey(),"service.ftl");
					process(hf,map,sFile.getValue(),"serviceImpl.ftl");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dbmsMetaUtil.close();
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	private void process(FreemarkerUtils hf,Map<String,Object> map,String file,String templateName) throws Exception {
		if (isExistFile(file)) {
			System.out.println(file + "  已经存在");
		} else {
			hf.process(map, file, templateName);
		}
	}

	private Map<String, Object> createMap(List<Map<String, Object>> columns,String tableName, String entityName, String entityRemark, Map<String, Object> pkMap) {
		Map<String, Object> map = ColumnUtil.columnToMap(columns, entityName, entityRemark);
		String pkKey = String.valueOf(pkMap.get("column"));
		String pkVal = String.valueOf(pkMap.get("columnName"));
		String pkType = String.valueOf(pkMap.get("simpleJavaType"));
		String firstLowerEntityName = ColumnUtil.lowerFirstChar(entityName);
		map.put("pkKey", pkKey);
		map.put("pkVal", pkVal);
		map.put("pkType",pkType);
		map.put("entityName", entityName);
		map.put("firstLowerEntityName", firstLowerEntityName);
		map.put("mapper",firstLowerEntityName+"Mapper");
		map.put("tableName", tableName);
		map.put("servicePackage",this.pathInfo.getServicePackage().getKey());
		map.put("serviceImplPackage",this.pathInfo.getServicePackage().getValue());
		map.put("serviceFile",this.pathInfo.getServiceFilePackage(entityName).getKey());
		map.put("entityPackage",this.pathInfo.getPersistencePackage().getKey());
		map.put("mapperPackage",this.pathInfo.getPersistencePackage().getValue());


		Pair<String, String> pFilePackage = this.pathInfo.getPersistenceFilePackage(entityName);
		map.put("mapperFile",pFilePackage.getValue());
		map.put("entityFile",pFilePackage.getKey());
		String resultMap = creatResultStr(columns);
		String baseColumnList = creatbaseColumnListStr(columns, entityName);
		map.put("resultMap", resultMap);
		map.put("baseColumnList",baseColumnList);
		map.put("insertSql", buildInsertSql(columns,tableName));
		map.put("updateSql",buildUpdateSql(columns,tableName,pkKey,pkVal));
		map.put("listSql",buildListSql(columns,tableName));
		map.put("listCountSql",buildListCountSql(columns,tableName,pkKey));
		return map;
	}
	private String buildInsertSql(List<Map<String, Object>> columns, String tableName) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("insert into  " + tableName);
		sbBuffer.append("\r\n\t\t<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\"> ");
		for (int i = 0; i < columns.size(); i++) {
			Map<String, Object> column = columns.get(i);
			sbBuffer.append("\r\n \t\t\t<if test=\"" + column.get("columnNameLower") + " != null\">");
			sbBuffer.append("\r\n \t\t\t\t" + column.get("column") + ",");
			sbBuffer.append("\r\n \t\t\t</if>");
		}
		sbBuffer.append("\r\n\t\t</trim>");

		sbBuffer.append("\r\n\t\t<trim prefix=\"values (\" suffix=\")\" suffixOverrides=\",\" >");
		for (int i = 0; i < columns.size(); i++) {
			Map<String, Object> column = columns.get(i);
			sbBuffer.append("\r\n \t\t\t<if test=\"" + column.get("columnNameLower") + " != null\">");
			sbBuffer.append("\r\n \t\t\t\t#{" + column.get("columnNameLower") + "},");
			sbBuffer.append("\r\n \t\t\t</if>");
		}
		sbBuffer.append("\r\n\t\t</trim>");
		return sbBuffer.toString();
	}
	private String buildUpdateSql(List<Map<String, Object>> columns, String tableName, String pkKey, String pkVal) {

		StringBuffer sbBuffer = new StringBuffer();
		sbBuffer.append("update   " + tableName);
		sbBuffer.append("\r\n \t\t <set>");
		for (int i = 0; i < columns.size(); i++) {
			Map<String, Object> column = columns.get(i);
			if (column.get("column").equals(pkKey) == false) {
				sbBuffer.append("\r\n \t\t\t <if test=\"" + column.get("columnNameLower") + " != null\">");
				sbBuffer.append("\r\n \t\t\t\t").append(column.get("column")).append(" = ").append("#{").append(column.get("columnNameLower")).append("},");
				sbBuffer.append("\r\n \t\t\t </if>");
			}
		}
		sbBuffer.append("\r\n \t\t </set>");
		sbBuffer.append("\r\n     where " + pkKey + " = #{" + pkVal + "}");
		return sbBuffer.toString();
	}

	private String buildListSql(List<Map<String, Object>> columns, String tableName) {

		StringBuffer sb = new StringBuffer();
		sb.append("select ");
		sb.append("\r\n \t\t").append("<include refid=\"Base_Column_List\" />");
		sb.append("\r\n \t\t").append("from ").append(tableName).append(" t" );
		sb.append("\r\n \t\t").append("<where>");
		for (int i = 0; i < columns.size(); i++) {
			Map<String, Object> column = columns.get(i);
			sb.append("\r\n \t\t\t").append("<if test=\"" + column.get("columnNameLower") + " != null\">");
			sb.append("\r\n \t\t\t\t").append("and t." + column.get("column") + " = " + "#{" + column.get("columnNameLower") + "}");
			sb.append("\r\n \t\t\t").append("</if>");
		}
		sb.append("\r\n\t\t").append("</where>");

		return sb.toString();
	}

	private String buildListCountSql(List<Map<String, Object>> columns, String tableName, String pkKey) {

		StringBuffer sb = new StringBuffer();
		sb.append("select ").append(" count(t." + pkKey + ")").append(" from " + tableName + " t" );
		sb.append("\r\n\t\t").append("<where> ");
		for (int i = 0; i < columns.size(); i++) {
			Map<String, Object> column = columns.get(i);
			sb.append("\r\n \t\t\t").append("<if test=\"" + column.get("columnNameLower") + " != null\">");
			sb.append("\r\n \t\t\t\t").append("and t." + column.get("column") + " = " + "#{" + column.get("columnNameLower") + "}");
			sb.append("\r\n \t\t\t").append("</if>");
		}
		sb.append("\r\n\t\t").append("</where>");
		return sb.toString();
	}
	private String creatResultStr(List<Map<String, Object>> columns) {
		StringBuffer sbBuffer = new StringBuffer();
		int len = columns.size();
		for (int i = 0; i < len; i++) {
			Map<String, Object> column = columns.get(i);
			if (i == 0) {
				sbBuffer.append("<id column=\"" + column.get("columnNameLower") + "\" property=\""
						+ column.get("columnNameLower") + "\" /> \r\n");
			} else {
				sbBuffer.append("\t\t  <id column=\"" + column.get("columnNameLower") + "\" property=\""
						+ column.get("columnNameLower") + "\" /> \r\n");
			}
		}

		return sbBuffer.toString();
	}

	private String creatbaseColumnListStr(List<Map<String, Object>> columns, String entityName) {
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
	private void mkdir(){
		Pair<String, String> pPath = this.pathInfo.getPersistenceAbsolutePath();
		createDir(pPath.getKey());
		createDir(pPath.getValue());
		Pair<String, String> sPath = this.pathInfo.getServiceAbsolutePath();
		createDir(sPath.getValue());
		//xmlpath
		createDir(this.pathInfo.getXmlPath());
    }



	public static boolean isExistFile(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("包名已经存在！[" + destDirName + "]");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！[" + destDirName + "]");
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}
}
