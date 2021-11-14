package com.yxkong.code.tools;

import java.util.List;
import java.util.Map;

public class TableInfo {
	public String className;
	public String entityRemark;
	public String classNameFirstLower;
	public String createTime;
	public List<Map<String,Object>>  columns;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getClassNameFirstLower() {
		return classNameFirstLower;
	}
	public void setClassNameFirstLower(String classNameFirstLower) {
		this.classNameFirstLower = classNameFirstLower;
	}
	public List<Map<String, Object>> getColumns() {
		return columns;
	}
	public void setColumns(List<Map<String, Object>> columns) {
		this.columns = columns;
	}
	public String getEntityRemark() {
		return entityRemark;
	}
	public void setEntityRemark(String entityRemark) {
		this.entityRemark = entityRemark;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	
}
