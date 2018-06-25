package cn.yan.study.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/25
 * Time  19:27
 */
public class ObjectUtils {
    public static boolean isEmpty(Object object) {
        return object == null?true:(object instanceof String?StrUtils.isEmpty((String)object):(object instanceof CharSequence?((CharSequence)object).length() == 0:(object instanceof Collection ?((Collection)object).isEmpty():(object instanceof Map ?((Map)object).isEmpty():(object.getClass().isArray()? Array.getLength(object) == 0:false)))));
    }
}
