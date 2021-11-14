//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.yxkong.code.tools;

import com.yxkong.code.utils.DateUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class ColumnUtil {
    public ColumnUtil() {
    }

    public static Map<String, Object> columnToMap(List<Map<String, Object>> columns, String tableName, String entityRemark) {
        boolean hasDateType = false;
        boolean hasBigDecimal = false;
        TableInfo tableInfo = new TableInfo();
        tableInfo.setClassName(tableName);
        tableInfo.setClassNameFirstLower(tableName);
        tableInfo.setEntityRemark(entityRemark);
        tableInfo.setCreateTime(DateUtil.dateToString(new Date(), DateUtil.DateStyle.YYYY_MM_DD_HH_MM_SS_CN));
        tableInfo.setColumns(columns);
        if (!hasDateType) {
            hasDateType = hasDateType(columns);
        }

        if (!hasBigDecimal) {
            hasBigDecimal = hasBigDecimal(columns);
        }

        Map<String, Object> map = new HashMap();
        map.put("table", tableInfo);
        map.put("import", hasDateType);
        map.put("hasBigDecimal", hasBigDecimal);
        return map;
    }

    public static List<Map<String, Object>> mysqlColumnsToJavaColumns(List<Map<String, Object>> columns) {
        List<Map<String, Object>> realColumns = new ArrayList();
        final Iterator<Map<String, Object>> iterator = columns.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> temp = iterator.next();
            Map<String, Object> column = new HashMap();
            column.put("simpleJavaType", mysqlTypeToJavaType(temp.get("type_name")));
            column.put("mysqlType", temp.get("type_name"));
            column.put("columnNameLower", mysqlColumnToJavaColumn(temp.get("column_name")));
            column.put("columnName", captureName(mysqlColumnToJavaColumn(temp.get("column_name"))));
            column.put("column", String.valueOf(temp.get("column_name")));
            column.put("remarks", temp.get("remarks"));
            column.put("isDateTimeColumn", false);
            realColumns.add(column);
        }

        return realColumns;
    }

    public static String mysqlColumnToJavaColumn(Object mysqlColumn) {
        StringBuffer sbBuffer = new StringBuffer();
        String[] arr = String.valueOf(mysqlColumn).split("_");
        if (arr.length > 1) {
            for (int i = 0; i < arr.length; ++i) {
                if (i == 0) {
                    sbBuffer.append(arr[i].toLowerCase());
                } else {
                    sbBuffer.append(captureName(arr[i].toLowerCase()));
                }
            }
        } else {
            sbBuffer.append(arr[0]);
        }

        return sbBuffer.toString();
    }
    public static boolean hasAssignType(List<Map<String, Object>> columns,String type){
        Iterator<Map<String, Object>> iterator = columns.iterator();

        while (iterator.hasNext()) {
            Map<String, Object> map = iterator.next();
            Object mysqlType = map.get("simpleJavaType");
            if (type.equals(String.valueOf(mysqlType).toLowerCase().trim())) {
                return true;
            }
        }

        return false;
    }
    public static boolean hasDateType(List<Map<String, Object>> columns) {
        return hasAssignType(columns,"date");
    }

    public static boolean hasBigDecimal(List<Map<String, Object>> columns) {
        return hasAssignType(columns,"bigdecimal");
    }

    public static String mysqlTypeToJavaType(Object mysqlType) {
        String type = String.valueOf(mysqlType).toLowerCase().trim();
        switch (type){
            case "char":
            case "longtext":
            case "varchar":
            case "blob":
            case "tinyblob":
            case "longblob":
                return "String";
            case "date":
            case "datetime":
            case "timestamp":
                return "Date";
            case "smallint":
            case "smallint unsigned":
            case "tinyint":
            case "tinyint unsigned":
            case "int":
            case "int unsigned":
            case "bit":
                return "Integer";
            case "bigint":
                return "Long";
            case "decimal":
            case "decimal unsigned":
            case "numeric":
            case "numeric unsigned":
            case "float":
            case "float unsigned":
            case "double":
            case "double unsigned":
                return "BigDecimal";
            default:
                return null;
        }
    }

    public static String captureName(String str) {
        char[] cs = str.toCharArray();
        cs[0] = (char) (cs[0] - 32);
        return String.valueOf(cs);
    }

    public static String lowerFirstChar(String str) {
        char[] chars = new char[]{str.charAt(0)};
        String temp = new String(chars);
        return chars[0] >= 'A' && chars[0] <= 'Z' ? str.replaceFirst(temp, temp.toLowerCase()) : str;
    }

    private static String[] arr = {"t_", "t_wk_", "_tb", "wk_", "c_"};

    public static String getEntityName(String tableName) {
        for (String prefix : arr) {
            if (StringUtils.startsWith(tableName, prefix)) {
                tableName = tableName.replaceFirst(prefix, "");
                break;
            }
        }
        return captureName(mysqlColumnToJavaColumn(tableName));
    }
}
