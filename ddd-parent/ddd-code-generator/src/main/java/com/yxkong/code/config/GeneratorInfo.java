package com.yxkong.code.config;


import com.yxkong.code.tools.DBMSMetaUtil;

import java.util.Objects;

/**
 *
 * @Author: yxkong
 * @Date: 2021/11/7 11:26 AM
 * @version: 1.0
 */
public class GeneratorInfo {
    private String rootPath;
    /**
     * 数据库的host地址
     */
    private String host;
    /**
     * 数据库端口号
     */
    private Integer port ;
    /**
     * 数据库名称
     */
    private String dbName;
    /**
     * 数据库用户名
     */
    private String userName;
    /**
     * 数据库密码
     */
    private String password;

    private String groupId;

    private String persistencePackage;
    private String xmlDir;
    /**
     * 模块名称
     */
    private String bizModule;
    /**
     * 哪些表,默认*
     */
    private String tableNames;
    /**
     * 数据表前缀去除
     */
    private String rmPrefix;
    /**
     * 数据库类型
     */
    private DBMSMetaUtil.DatabaseType databaseType ;
    /**
     * 代码生成类型，0，api，1 后台管理项目
     */
    private Integer type;
    public GeneratorInfo(Builder builder) {
        this.rootPath = builder.rootPath;
        this.host = builder.host;
        this.port = builder.port;
        this.userName = builder.userName;
        this.password = builder.password;
        this.dbName = builder.dbName;
        this.tableNames = builder.tableNames;
        this.persistencePackage = builder.persistencePackage;
        this.groupId = builder.groupId;
        this.bizModule = builder.bizModule;
        this.rmPrefix = builder.rmPrefix;
        this.xmlDir = builder.xmlDir;
        this.databaseType = builder.databaseType;
        this.type = builder.type;
    }

    public static class  Builder{

        private String rootPath = System.getProperty("user.dir");
        private String host = "localhost";
        private Integer port = 3306;
        private String userName;
        private String password;
        private String dbName;
        private String tableNames = "*";
        private String groupId;
        private String persistencePackage;
        private String xmlDir ;
        private String bizModule = "code";
        private String rmPrefix;
        /**
         * 代码生成类型，0，api，1 后台管理项目
         */
        private Integer type = 0;
        /**
         * 默认数据库类型
         */
        private DBMSMetaUtil.DatabaseType databaseType = DBMSMetaUtil.DatabaseType.MYSQL;
        public GeneratorInfo build(){

            if(isEmpty(this.userName)){
                throw new RuntimeException("必须参数userName不能为空！");
            }
            if(isEmpty(this.password)){
                throw new RuntimeException("必须参数password不能为空！");
            }
            if(isEmpty(this.dbName)){
                throw new RuntimeException("必须参数dbName不能为空！");
            }
            if (isEmpty(this.xmlDir)){
                this.xmlDir = this.dbName;
            }
            if (isEmpty(persistencePackage)){
                this.persistencePackage  = groupId+".infrastructure.persistence";
            }

            return new GeneratorInfo(this);
        }

        /**
         * 数据库ip
         * @param host
         * @return
         */
        public Builder host(String host){
            this.host = trim(host);
            return this;
        }

        /**
         * 数据库端口号
         * @param port
         * @return
         */
        public Builder port(Integer port){
            this.port = port;
            return this;
        }
        public Builder type(Integer type){
            this.type = type;
            return this;
        }

        /**
         * 数据库用户名
         * @param userName
         * @return
         */
        public Builder userName(String userName){
            this.userName = trim(userName);
            return this;
        }

        /**
         * 数据库密码
         * @param password
         * @return
         */
        public Builder password(String password){
            this.password = trim(password);
            return this;
        }

        /**
         * 数据库名称
         * @param dbName
         * @return
         */
        public Builder dbName(String dbName){
            this.dbName = trim(dbName);
            return this;
        }

        /**
         * 要生成的表
         * @param tableNames
         * @return
         */
        public Builder tableNames(String tableNames){
            this.tableNames = trim(tableNames);
            return this;
        }

        /**
         * 数据库类型
         * @param databaseType
         * @return
         */
        public Builder databaseType(DBMSMetaUtil.DatabaseType databaseType){
            this.databaseType = databaseType;
            return this;
        }

        /**
         * 业务模块名称
         * @param bizModule
         * @return
         */
        public Builder bizModule(String bizModule){
            this.bizModule = trim(bizModule);
            return this;
        }

        /**
         * 一般是package的公共部分
         * @param groupId
         * @return
         */
        public Builder groupId(String groupId){
            this.groupId = trim(groupId);
            return this;
        }


        /**
         * xml目录一般跟着数据库走，不要带前缀和下划线
         * @param xmlDir
         * @return
         */
        public Builder xmlDir(String xmlDir){
            this.xmlDir = trim(xmlDir);
            return this;
        }

        /**
         * 移除表前缀
         * @param prefix
         * @return
         */
        public Builder rmPrefix(String prefix){
            this.rmPrefix = trim(prefix);
            return this;
        }
        public Builder rootPath(String rootPath){
            this.rootPath = trim(rootPath);
            return this;
        }
        private boolean isEmpty(String str){
            if(Objects.isNull(str)||"".equals(str)||"null".equals(str)){
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        public  String trim(String str){
            if(null != str){
                str = str.trim();
            }
            return str;
        }
    }

    public String getRootPath() {
        return rootPath;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public String getDbName() {
        return dbName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getPersistencePackage() {
        return persistencePackage;
    }

    public String getXmlDir() {
        return xmlDir;
    }

    public String getBizModule() {
        return bizModule;
    }

    public String getTableNames() {
        return tableNames;
    }

    public String getRmPrefix() {
        return rmPrefix;
    }

    public DBMSMetaUtil.DatabaseType getDatabaseType() {
        return databaseType;
    }
}
