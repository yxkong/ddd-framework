package com.yxkong.code.config;

import com.yxkong.code.tools.*;

import java.io.File;
import java.util.*;

public class CodeGenerator {

	private static Map<String, String> paramsMap = new HashMap<String, String>();
	public static ResourceBundle bundle = null;


	private String projectPath = System.getProperty("user.dir");
	private String classPath = projectPath + File.separator+"src"+File.separator+"main"+File.separator+"java";
	private String resourcesPath = projectPath +  File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator;

	private  GeneratorInfo generatorInfo;

    public CodeGenerator(GeneratorInfo info){
		this.generatorInfo = info;
	}
	/**
	 * 通过key 获取pwd
	 * 
	 * @param key
	 * @return
	 */
	public static String getVal(String key) {
		return paramsMap.get(key);
	}
	private String persistencePath;
	private String xmlPath;
	public void run() {
		try {
			if (generatorInfo == null){
				throw new RuntimeException("generatorInfo 不能为空");
			}
			DBMSMetaUtil dbmsMetaUtil = new DBMSMetaUtil(generatorInfo);
			String[] tableNameArray = null;
			if (generatorInfo.getTableNames().contains("*")){
				List<Map<String, Object>> maps = dbmsMetaUtil.listTables();
				maps.forEach(s-> System.out.println(s.toString()));
			} else {
				tableNameArray = generatorInfo.getTableNames().split(",");
			}
			this.mkdir();
			for (String tableName:tableNameArray){
				System.out.println(tableName);
				Map<String, Object> tableMap = dbmsMetaUtil.selectTable(tableName);
				List<Map<String, Object>> columns = dbmsMetaUtil.listColumns(tableName);
				List<Map<String, Object>> pkColumns = dbmsMetaUtil.listPkColumn( tableName);

				//
				tableMap = MapUtil.convertKey2LowerCase(tableMap);
				columns = MapUtil.convertKeyList2LowerCase(columns);
				pkColumns = MapUtil.convertKeyList2LowerCase(pkColumns);

				String entityName = ColumnUtil.getEntityName(tableName);
				String entityRemark = String.valueOf(tableMap.get("remarks"));

				columns = ColumnUtil.mysqlColumnsToJavaColumns(columns);
				Map<String, Object> map = ColumnUtil.columnToMap(columns, entityName, entityRemark);
				map.put("persistencePackage",this.generatorInfo.getPersistencePackage());
				map.put("bizModule",generatorInfo.getBizModule());
				FreemarkerUtils hf = new FreemarkerUtils();

				try {
					hf.init(resourcesPath);
					// 生成实体DO对象和mapper
					String doPath =  this.persistencePath + "entity"+File.separator+this.generatorInfo.getBizModule()+File.separator;
					String doFile = entityName + "DO.java";
					if (isExistFile(doPath+doFile)) {
						System.out.println(doPath+doFile + "  已经存在");
					} else {
						hf.process(map, doPath, doFile, "do.ftl");
					}
					String mapperPath =  this.persistencePath + "mapper"+File.separator+this.generatorInfo.getBizModule()+File.separator;
					String mapperFile = entityName + "Mapper.java";
					if (isExistFile(mapperPath+mapperFile)) {
						System.out.println(mapperPath+mapperFile + "  已经存在");
					} else {
						hf.process(map, mapperPath, mapperFile, "mapper.ftl");
					}
					//生成xml
					Map<String, Object> mapperMap = MapperUtil.createMapper(this.generatorInfo.getPersistencePackage(),this.generatorInfo.getBizModule(), entityName, tableName, columns, pkColumns.get(0));
					String xmlFile = entityName + "Mapper.xml";
					if (isExistFile(xmlPath + xmlFile)) {
						System.out.println(xmlPath + xmlFile + " 已经存在");
					} else {
						hf.process(mapperMap, xmlPath , xmlFile, "mapperXml.ftl");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			dbmsMetaUtil.close();
		}catch (Exception e){
			e.printStackTrace();
		}

	}

    private void mkdir(){
	    String[] baseArrs = this.generatorInfo.getPersistencePackage().split("\\.");
		this.persistencePath = this.classPath;
	    if (baseArrs != null) {
		    for (String path : baseArrs) {
				this.persistencePath = persistencePath + File.separator + path;
		    }
	    }
	    //构建persistencePackage
		this.persistencePath = persistencePath + File.separator;
	    createDir(persistencePath + "entity" + File.separator+this.generatorInfo.getBizModule()+File.separator);
	    createDir(persistencePath + "mapper" + File.separator+this.generatorInfo.getBizModule()+File.separator);

		//xmlPackage
		xmlPath = resourcesPath +"mybatis" + File.separator+ "mapper" +File.separator+ this.generatorInfo.getXmlDir() + File.separator;
		createDir(xmlPath);
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
