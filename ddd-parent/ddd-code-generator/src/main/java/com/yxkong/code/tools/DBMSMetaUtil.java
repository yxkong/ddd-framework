package com.yxkong.code.tools;

import com.yxkong.code.config.GeneratorInfo;

import java.io.Closeable;
import java.io.IOException;
import java.sql.*;
import java.util.*;


/** 
 * 需要注意,想要有表字段描述信息，获取连接时需要指定某些特殊属性<br/>  
 * 数据交换-工具类 
 */  
public class DBMSMetaUtil implements Closeable {

    private GeneratorInfo info;
    private Connection conn;

    /**
     * 建立链接
     * @param info
     */
    public DBMSMetaUtil(GeneratorInfo info) throws SQLException{
        this.info = info;
        init();
    }
    private void init() throws SQLException  {
        String url = this.concatDbUrl();
        // 不需要加载Driver. Servlet 2.4规范开始容器会自动载入
        // conn = DriverManager.getConnection(url, username, password);
        //
        Properties properties =new Properties();
        //
        properties.put("user", this.info.getUserName());
        properties.put("password", this.info.getPassword());
        // !!! Oracle 如果想要获取元数据 REMARKS 信息,需要加此参数
        properties.put("remarksReporting","true");
        // !!! MySQL 标志位, 获取TABLE元数据 REMARKS 信息
        properties.put("useInformationSchema","true");
        // 不知道SQLServer需不需要设置...
        //
        this.conn = DriverManager.getConnection(url, properties);
    }

    @Override
    public void close() throws IOException {
        close(conn);
    }

    /**
     * 数据库类型,枚举 
     *  
     */  
    @SuppressWarnings("AlibabaEnumConstantsMustHaveComment")
    public static enum DatabaseType {
        /**
         * o
         */
        ORACLE,
        MYSQL,
        SQLSERVER,
        SQLSERVER2005,
        DB2,
        INFORMIX,
        SYBASE,
        OTHER,
        EMPTY
    }  
  
    /** 
     * 根据字符串,判断数据库类型 
     *  
     * @param databaseType
     * @return 
     */  
    private  DatabaseType parseDatabaseType(String databaseType) {

        // 空类型  
        if (null == databaseType || databaseType.trim().length() < 1) {
            return DatabaseType.EMPTY;
        }  
        // 截断首尾空格,转换为大写  
        databaseType = databaseType.trim().toUpperCase();
        for (DatabaseType type: DatabaseType.values()){
            if (type.name().equals(databaseType)){
                return type;
            }
            if (databaseType.contains("SERVER") && databaseType.contains("20")){
                try {
                    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                    return DatabaseType.SQLSERVER2005;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                try {
                    // 注册 JTDS
                    Class.forName("net.sourceforge.jtds.jdbc.Driver");
                    return DatabaseType.SQLSERVER;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
        // 默认,返回其他  
        return DatabaseType.OTHER;
    }  
  
    /** 
     * 列出数据库的所有表 
     */  
    public List<Map<String, Object>> listTables() {
        return findTable(null);
    }
    private List<Map<String, Object>> findTable(String tableNamePattern ){
        List<Map<String, Object>> result = null;
        ResultSet rs = null;
        String catalog = null;
        String schemaPattern = null;
        String[] types = { "TABLE" };
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // 获取Meta信息对象
            DatabaseMetaData meta = conn.getMetaData();
            DatabaseType dbType = this.info.getDatabaseType();
            if (DatabaseType.MYSQL.equals(dbType)){
                catalog = this.info.getDbName();
                schemaPattern = this.info.getDbName();
            } else if ((DatabaseType.ORACLE.equals(dbType))){
                schemaPattern = this.info.getUserName();
                if (null != schemaPattern) {
                    schemaPattern = schemaPattern.toUpperCase();
                }
            } else if (DatabaseType.DB2.equals(dbType)) {
                schemaPattern = "jence_user";
            }else {
                tableNamePattern = "%";
            }
            rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);
            result = parseResultSetToMapList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
        }
        return convertKeyList2LowerCase(result);
    }
    /**
     * 获取表的元信息
     * @param tableNamePattern
     * @return
     */
    public Map<String, Object> selectTable(String tableNamePattern) {
         return findTable(tableNamePattern).get(0);
    }  
  
    /** 
     * 列出表的所有字段 
     */  
    public List<Map<String, Object>> listColumns( String tableName) {
        return findColumns(tableName,1);
    }

    /**
     *
     * @param tableName
     * @param type 1 列出所有字段，0 列出主键
     * @return
     */
    private List<Map<String, Object>> findColumns(String tableName,Integer type){
        List<Map<String, Object>> result = null;
        ResultSet rs = null;
        //
        try {
            // 获取Meta信息对象
            DatabaseMetaData meta = conn.getMetaData();
            // 数据库
            String catalog = this.info.getDbName();
            // 数据库的用户
            String schemaPattern = null;// meta.getUserName();
            // 表名
            String tableNamePattern = tableName;//
            // 转换为大写
            if (null != tableNamePattern) {
                tableNamePattern = tableNamePattern.toUpperCase();
            }
            //
            String columnNamePattern = null;
            // Oracle
            if (DatabaseType.ORACLE.equals(this.info.getDatabaseType())) {
                // 查询
                schemaPattern = this.info.getUserName();
                if (null != schemaPattern) {
                    schemaPattern = schemaPattern.toUpperCase();
                }
            } else {
                //
            }

            if (null == type || type == 1){
                rs = meta.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);
            }else {
                rs=	meta.getPrimaryKeys(catalog, schemaPattern, tableNamePattern);
            }
            result = parseResultSetToMapList(rs);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            close(rs);
        }
        //
        return convertKeyList2LowerCase(result);
    }

    /**
     * 获取主键信息
     * @param tableName
     * @return
     */
    public List<Map<String, Object>> listPkColumn(String tableName) {
    	return findColumns(tableName,0);
    }  
  
    /** 
     * 根据IP,端口,以及数据库名字,拼接Oracle连接字符串 
     *
     * @return 
     */  
    public String concatDbUrl() {
        String colon = ":";
        String lash = "/";
        StringBuffer sb = new StringBuffer();
        // Oracle数据库  
        DatabaseType dbType = this.info.getDatabaseType();
        String host = this.info.getHost();
        Integer port = this.info.getPort();
        String dbName = this.info.getDbName();
        if (DatabaseType.ORACLE.equals(dbType)) {
            sb.append("jdbc:oracle:thin:@").append(host).append(colon).append(port).append(colon).append(dbName);
        } else if (DatabaseType.MYSQL.equals(dbType)) {
            sb.append("jdbc:mysql://").append(host).append(colon).append(port).append(lash).append(dbName).append("?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8");

        } else if (DatabaseType.SQLSERVER.equals(dbType)) {
            sb.append("jdbc:jtds:sqlserver://").append(host).append(colon).append(port).append(lash).append(dbName).append(";tds=8.0;lastupdatecount=true");
        } else if (DatabaseType.SQLSERVER2005.equals(dbType)) {
            sb.append("jdbc:sqlserver://").append(host).append(colon).append(port).append("; DatabaseName=").append(dbName);
        } else if (DatabaseType.DB2.equals(dbType)) {
            sb.append("jdbc:db2://").append(host).append(colon).append(port).append(lash).append(dbName);
        } else if (DatabaseType.INFORMIX.equals(dbType)) {
            sb.append("jdbc:informix-sqli://").append(host).append(colon).append(port).append(lash).append(dbName);
        } else if (DatabaseType.SYBASE.equals(dbType)) {
            sb.append("jdbc:sybase:Tds:").append(host).append(colon).append(port).append(lash).append(dbName);

        } else {  
            throw new RuntimeException("不认识的数据库类型!");  
        }  
        //  
        return sb.toString();
    }  

  
    /** 
     * 将一个未处理的ResultSet解析为Map列表. 
     *  
     * @param rs 
     * @return 
     */  
    public static List<Map<String, Object>> parseResultSetToMapList(ResultSet rs) {  
        //  
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();  
        //  
        if (null == rs) {  
            return null;  
        }  
        //  
        try {  
            while (rs.next()) {
                result.add(parseResultSetToMap(rs));
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        //  
        return result;  
    }  
  
    /** 
     * 解析ResultSet的单条记录,不进行 ResultSet 的next移动处理 
     *  
     * @param rs 
     * @return 
     */  
    private static Map<String, Object> parseResultSetToMap(ResultSet rs) {  
        //  
        if (null == rs) {  
            return null;  
        }  
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            ResultSetMetaData meta = rs.getMetaData();
            for (int i = 1; i <=  meta.getColumnCount(); i++) {
                // 列名
                map.put( meta.getColumnLabel(i), rs.getObject(i));
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return map;
    }  

    //  
    public static void close(Connection conn) {  
        if(conn!=null) {  
            try {  
                conn.close();  
                conn = null;
            } catch (SQLException e) {
                e.printStackTrace();  
            }  
        }  
    }  
    //  
    public static void close(Statement stmt) {  
        if(stmt!=null) {  
            try {  
                stmt.close();  
                stmt = null;
            } catch (SQLException e) {
                e.printStackTrace();  
            }  
        }  
    }  
    //  
    public static void close(ResultSet rs) {  
        if(rs!=null) {  
            try {  
                rs.close();  
                rs = null;  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }


    /**
     * 将List中的Key转换为小写
     * @param list 返回新对象
     * @return
     */
    private static  List<Map<String, Object>> convertKeyList2LowerCase(List<Map<String, Object>> list){
        if(null==list) {
            return null;
        }
        List<Map<String, Object>> resultList = new ArrayList<Map<String,Object>>();
        list.forEach(item ->{
            resultList.add(convertKey2LowerCase(item));
        });
        return resultList;
    }
    /**
     * 转换单个map,将key转换为小写.
     * @param map 返回新对象
     * @return
     */
    private static Map<String, Object> convertKey2LowerCase(Map<String, Object> map){
        if(null==map) {
            return null;
        }
        Map<String, Object> result = new HashMap<String, Object>();
        map.forEach((k,v)->{
            result.put(k.toLowerCase(),v);
        });
        return result;
    }

}