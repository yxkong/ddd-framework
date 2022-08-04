package com.yxkong.code.tools;

import com.yxkong.code.config.GeneratorInfo;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;
import java.util.Objects;

/**
 * <TODO>
 *
 * @Author: yxkong
 * @Date: 2022/6/11 8:44 PM
 * @version: 1.0
 */
public class PathAndPackageInfo {
    /**根路径*/
    private String rootPath ;
    /**java文件的目录根路径*/
    private String classRootPath;
    /**生成java文件的包名*/
    private String classRootPackage;
    /**mapper.xml的根路径*/
    private String resourcesRootPath;
    private String groupId;
    private String bizModulePath="";
    private String bizModule;
    public PathAndPackageInfo(GeneratorInfo info){
        this.rootPath = info.getRootPath();
        this.classRootPath = rootPath+File.separator+"src"+File.separator+"main"+File.separator+"java";
        this.resourcesRootPath = rootPath +  File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"mybatis"+File.separator;
        if (Objects.nonNull(info)){
            String[] paths = info.getGroupId().split("\\.");
            for (String path:paths){
                this.classRootPath += File.separator+path;
            }
            this.classRootPath += File.separator;
        }
        this.groupId = info.getGroupId();
        this.bizModule  = info.getBizModule();
        if (Objects.nonNull(info.getBizModule())){
            String[] paths = info.getBizModule().split("\\.");
            for (String path:paths){
                this.bizModulePath =this.bizModulePath+ path+File.separator;
            }
        }

    }

    /**
     * 获取持久层entity和mapper的path
     * @return
     */
    public Pair<String,String> getPersistenceAbsolutePath(){
        String persistencePath = this.classRootPath + "infrastructure"+ File.separator+"persistence"+File.separator;
        String entityPath = persistencePath +  "entity" + File.separator+this.bizModule+File.separator;
        String mapperPath = persistencePath +  "mapper" + File.separator+this.bizModule+File.separator;
        return Pair.of(entityPath,mapperPath);
    }

    /**
     * 获取对应entity和mapper的package
     * @return
     */
    public Pair<String,String> getPersistencePackage(){
        String persistencePackage = this.groupId + ".infrastructure.persistence.";
        String entityPackage = persistencePackage +  "entity." + this.bizModule;
        String mapperPackage = persistencePackage +  "mapper." + this.bizModule;
        return Pair.of(entityPackage,mapperPackage);
    }

    /**
     * 获取对应的entity和mapper的文件path
     * @param entityName
     * @return
     */
    public Pair<String,String> getPersistenceAbsoluteFile(String entityName){
        Pair<String, String> pair = getPersistenceAbsolutePath();
        return Pair.of(pair.getKey()+entityName+"DO.java",pair.getValue()+entityName+"Mapper.java");
    }

    /**
     * 获取对应的entity和mapper的package
     * @param entityName
     * @return
     */
    public Pair<String,String> getPersistenceFilePackage(String entityName){
        Pair<String, String> pair = getPersistencePackage();
        return Pair.of(pair.getKey()+"."+entityName+"DO",pair.getValue()+"."+entityName+"Mapper");
    }

    /**
     * 获取service的路径
     * @return
     */
    public Pair<String,String> getServiceAbsolutePath(){
        String servicePath = this.classRootPath + "application"+File.separator+"service"+File.separator+this.bizModule+File.separator;
        return Pair.of(servicePath,servicePath+"impl"+File.separator);
    }

    /**
     * 获取service和impl的对应文件的包
     * @return
     */
    public Pair<String,String> getServicePackage(){
        String servicePath = this.groupId + ".application.service." + this.bizModule;
        return Pair.of(servicePath,servicePath+".impl");
    }

    /**
     * 获取service的文件
     * @param entityName
     * @return
     */
    public Pair<String,String> getServiceAbsoluteFile(String entityName){
        Pair<String, String> pair = getServiceAbsolutePath();
        return Pair.of(pair.getKey()+"I"+entityName+"Service.java",pair.getValue()+entityName+"ServiceImpl.java");
    }

    /**
     * 获取service和impl文件的import包；
     * @param entityName
     * @return
     */
    public Pair<String,String> getServiceFilePackage(String entityName){
        Pair<String, String> pair = getServicePackage();
        return Pair.of(pair.getKey()+".I"+entityName+"Service",pair.getValue()+"."+entityName+"ServiceImpl");
    }

    /**
     * 获取xml生成的path
     * @return
     */
    public String getXmlPath(){
        return  this.resourcesRootPath+this.bizModulePath;
    }
    public String getXmlFile(String entityName){
        return  getXmlPath()+entityName+"Mapper.xml";
    }

}
