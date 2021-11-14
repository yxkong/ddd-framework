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

    private DatabaseType dbType ;
    private String host;
    private int port;
    private String dbName;
    private String userName;
    private String password;
    private Connection conn;


    public DBMSMetaUtil(GeneratorInfo info){
        this.dbType = info.getDatabaseType();
        this.host = info.getHost();
        this.port = info.getPort();
        this.dbName = info.getDbName();
        this.userName = info.getUserName();
        this.password = info.getPassword();
        init();
    }
    private void init()  {
        String url = this.concatDbUrl();
        Connection conn = null;
        try {
            // 不需要加载Driver. Servlet 2.4规范开始容器会自动载入
            // conn = DriverManager.getConnection(url, username, password);
            //
            Properties info =new Properties();
            //
            info.put("user", this.userName);
            info.put("password", this.password);
            // !!! Oracle 如果想要获取元数据 REMARKS 信息,需要加此参数
            info.put("remarksReporting","true");
            // !!! MySQL 标志位, 获取TABLE元数据 REMARKS 信息
            info.put("useInformationSchema","true");
            // 不知道SQLServer需不需要设置...
            //
            conn = DriverManager.getConnection(url, info);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        this.conn = conn;
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
        for (DatabaseType type:DatabaseType.values()){
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
        List<Map<String, Object>> result = null;
        ResultSet rs = null;
        //  
        try {
            this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            // 获取Meta信息对象  
            DatabaseMetaData meta = conn.getMetaData();  
            // 数据库  
            String catalog = null;  
            // 数据库的用户  
            String schemaPattern = null;
            // 表名  
            String tableNamePattern = null;
            // types指的是table、view  
            String[] types = { "TABLE" };  
            // Oracle  
            if (DatabaseType.ORACLE.equals(dbType)) {
                schemaPattern = this.userName;
                if (null != schemaPattern) {  
                    schemaPattern = schemaPattern.toUpperCase();  
                }  
                // 查询  
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
            } else if (DatabaseType.MYSQL.equals(this.dbType)) {
                // Mysql查询  
                // MySQL 的 table 这一级别查询不到备注信息  
                schemaPattern = this.dbName;
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
            }  else if (DatabaseType.SQLSERVER.equals(dbType) || DatabaseType.SQLSERVER2005.equals(dbType)) {
                // SqlServer  
                tableNamePattern = "%";  
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
            }  else if (DatabaseType.DB2.equals(dbType)) {
                // DB2查询  
                schemaPattern = "jence_user";  
                tableNamePattern = "%";  
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
            } else if (DatabaseType.INFORMIX.equals(dbType)) {
                // SqlServer  
                tableNamePattern = "%";  
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
            } else if (DatabaseType.SYBASE.equals(dbType)) {
                // SqlServer  
                tableNamePattern = "%";  
                rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
            }  else {  
                throw new RuntimeException("不认识的数据库类型!");  
            }  
            //  
            result = parseResultSetToMapList(rs);  
  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        finally {
            close(rs);
        }
        //  
        return result;  
    }  
    public Map<String, Object> selectTable(String tableNamePattern) {

    	List<Map<String, Object>> result = null;
    	ResultSet rs = null;
    	//  
    	try {
    		this.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
    		// 获取Meta信息对象  
    		DatabaseMetaData meta = conn.getMetaData();  
    		// 数据库  
    		String catalog = null;
    		// 数据库的用户  
    		String schemaPattern = null;// meta.getUserName();  
    		// 表名  
//    		String tableNamePattern = null;//  
    		// types指的是table、view  
    		String[] types = { "TABLE" };  
    		// Oracle  
    		if (DatabaseType.ORACLE.equals(dbType)) {
    			schemaPattern = this.userName;
    			if (null != schemaPattern) {  
    				schemaPattern = schemaPattern.toUpperCase();  
    			}  
    			// 查询  
    			rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
    		} else if (DatabaseType.MYSQL.equals(dbType)) {
    			// Mysql查询  
    			// MySQL 的 table 这一级别查询不到备注信息
                catalog = dbName;
    			schemaPattern = dbName;
    			rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
    		}  else if (DatabaseType.SQLSERVER.equals(dbType) || DatabaseType.SQLSERVER2005.equals(dbType)) {
    			// SqlServer  
    			tableNamePattern = "%";  
    			rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
    		}  else if (DatabaseType.DB2.equals(dbType)) {
    			// DB2查询  
    			schemaPattern = "jence_user";  
    			tableNamePattern = "%";  
    			rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
    		} else if (DatabaseType.INFORMIX.equals(dbType)) {
    			// SqlServer  
    			tableNamePattern = "%";  
    			rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
    		} else if (DatabaseType.SYBASE.equals(dbType)) {
    			// SqlServer  
    			tableNamePattern = "%";  
    			rs = meta.getTables(catalog, schemaPattern, tableNamePattern, types);  
    		}  else {  
    			throw new RuntimeException("不认识的数据库类型!");  
    		}  
    		//  
    		result = parseResultSetToMapList(rs);  
    		
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	} finally {  
    		close(rs);  
    	}
    	//  
    	return result.get(0);
    }  
  
    /** 
     * 列出表的所有字段 
     */  
    public List<Map<String, Object>> listColumns( String tableName) {

        List<Map<String, Object>> result = null;  
        // Statement stmt = null;
        ResultSet rs = null;  
        //  
        try {  
            // 获取Meta信息对象  
            DatabaseMetaData meta = conn.getMetaData();  
            // 数据库  
            String catalog = this.dbName;
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
            if (DatabaseType.ORACLE.equals(dbType)) {
                // 查询  
                schemaPattern = userName;
                if (null != schemaPattern) {  
                    schemaPattern = schemaPattern.toUpperCase();  
                }  
            } else {  
                //  
            }  
  
            rs = meta.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);  
            // 获取主键列,但还没使用
            meta.getPrimaryKeys(catalog, schemaPattern, tableNamePattern);
            //  
            result = parseResultSetToMapList(rs);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭资源  
            close(rs);  
        }
        //  
        return result;  
    }  
    public List<Map<String, Object>> listPkColumn(String tableName) {

    	List<Map<String, Object>> result = null;
    	// Statement stmt = null;  
    	ResultSet rs = null;  
    	//  
    	try {  
    		// 获取Meta信息对象  
    		DatabaseMetaData meta = conn.getMetaData();  
    		// 数据库  
    		String catalog = null;  
    		// 数据库的用户  
    		String schemaPattern = null;// meta.getUserName();  
    		// 表名  
    		String tableNamePattern = tableName;//  
    		// 转换为大写  
    		if (null != tableNamePattern) {  
    			tableNamePattern = tableNamePattern.toUpperCase();  
    		}  
    		//   
//    		String columnNamePattern = null;  
    		// Oracle  
    		if (DatabaseType.ORACLE.equals(dbType)) {
    			// 查询  
    			schemaPattern = userName;
    			if (null != schemaPattern) {  
    				schemaPattern = schemaPattern.toUpperCase();  
    			}  
    		} else {  
    			//  
    		}  
    		
//    		rs = meta.getColumns(catalog, schemaPattern, tableNamePattern, columnNamePattern);  
    		// 获取主键列,但还没使用
    	rs=	meta.getPrimaryKeys(catalog, schemaPattern, tableNamePattern);  
    		//  
    		result = parseResultSetToMapList(rs);  
    	} catch (Exception e) {  
    		e.printStackTrace();  
    	} finally {  
    		// 关闭资源  
    		close(rs);  
    	}
    	//  
    	return result;  
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
        if (DatabaseType.ORACLE.equals(dbType)) {
            //  
            sb.append("jdbc:oracle:thin:@").append(this.host).append(colon).append(port).append(colon).append(dbName);

            String  url2 ="jdbc:oracle:thin:@(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = "
                    + this.host +")(PORT ="+ port +")))(CONNECT_DATA = (SERVICE_NAME ="+this.dbName+
                    ")(FAILOVER_MODE = (TYPE = SELECT)(METHOD = BASIC)(RETRIES = 180)(DELAY = 5))))";  
            //  
            // url = url2;  
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
                //   
                Map<String, Object> map = parseResultSetToMap(rs);  
                //  
                if (null != map) {  
                    result.add(map);  
                }  
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
        //  
        Map<String, Object> map = new HashMap<String, Object>();  
        //  
        try {  
            ResultSetMetaData meta = rs.getMetaData();  
            //  
            int colNum = meta.getColumnCount();  
            //  
            for (int i = 1; i <= colNum; i++) {  
                // 列名  
                String name = meta.getColumnLabel(i); // i+1  
                Object value = rs.getObject(i);  
                // 加入属性  
                map.put(name, value);  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        //  
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

}