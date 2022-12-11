package vip.maosi.util;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResultSetUtil {
    /**
     * 根据结果集合，映射到实体
     *
     * @param rs    结果集
     * @param clazz 实体类
     * @param <T>   实体
     * @return
     */
    public static <T> T readEntity(ResultSet rs, Class<T> clazz) {
        ResultSetMetaData rsmd = null;
        Method method = null;
        T t = null;
        try {
            rsmd = rs.getMetaData();
            String columnName;
            String key;
            if (rs.next()) {
                t = clazz.newInstance();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i);
                    key = StringUtil.toJavaAttributeName(columnName);
                    method = clazz.getDeclaredMethod(StringUtil.asserSetMethodName(key),String.class);
                    method.invoke(t, rs.getString(columnName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * 根据结果集合，返回map结构
     * @param rs 结果集
     * @return
     */
    public static Map readEntity(ResultSet rs) {
        ResultSetMetaData rsmd = null;
        Method s = null;
        Map<String,Object> map = new HashMap<>();

        try {
            rsmd = rs.getMetaData();
            String columnName;
            String key;
            if (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i);
                    key = StringUtil.toJavaAttributeName(columnName);
                    map.put(key, rs.getObject(columnName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 根据结果集合，映射到实体
     * @param rs 结果集
     * @param clazz 实体类
     * @param <T> 实体
     * @return
     */
    public static <T> List<T> readList(ResultSet rs, Class<T> clazz) {
        ResultSetMetaData rsmd = null;
        List<T> list = new ArrayList<T>();
        Method method = null;
        T t = null;
        try {
            rsmd = rs.getMetaData();
            String columnName;
            String key;
            while (rs.next()) {
                t = clazz.newInstance();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i);
                    key = StringUtil.toJavaAttributeName(columnName);
                    method = clazz.getDeclaredMethod(StringUtil.asserSetMethodName(key), String.class);
                    method.invoke(t, rs.getString(columnName));
                }
                list.add(t);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 根据结果集合，映射到Map
     * @param rs 结果集
     * @return
     */
    public static List<Map> readList(ResultSet rs) {
        ResultSetMetaData rsmd;
        List<Map> list = new ArrayList<>();
        try {
            rsmd = rs.getMetaData();
            String columnName;
            String key;
            while (rs.next()) {
                Map<String,Object> map = new HashMap<>();
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    columnName = rsmd.getColumnName(i);
                    key = StringUtil.toJavaAttributeName(columnName);
                    map.put(key, rs.getObject(columnName));
                }
                list.add(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
