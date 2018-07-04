package cn.yan.study.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/25
 * Time  19:27
 */
public class YanObjectUtils {
    public static boolean isEmpty(Object object) {
        return object == null || (object instanceof String ? YanStrUtils.isEmpty((String) object) : (object instanceof CharSequence ? ((CharSequence) object).length() == 0 : (object instanceof Collection ? ((Collection) object).isEmpty() : (object instanceof Map ? ((Map) object).isEmpty() : (object.getClass().isArray() && Array.getLength(object) == 0)))));
    }

    public static Map<String, Object> getObjMap(Object obj) {
        try {
            Map<String,Object> map = new HashMap<>();

            Class clazz = obj.getClass();
            Field[] fields = clazz.getDeclaredFields();
            String fieldName;
            Object object;
            for (int i=0; i < fields.length ; i++) {
                fieldName = fields[i].getName();
                fields[i].setAccessible(true);
                object = fields[i].get(obj);
                map.put(fieldName,object);
            }
            return map;
        } catch (Exception e) {
            return null;
        }


    }
}
