package cn.yan.study.utils;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/25
 * Time  19:28
 */
public class StrUtils {
    public static boolean isEmpty(String str) {
        if(str != null && str.length() != 0) {
            for(int i = 0; i < str.length(); ++i) {
                char c = str.charAt(i);
                if(c != 32 && c != 9 && c != 13 && c != 10) {
                    return false;
                }
            }

            return true;
        } else {
            return true;
        }
    }
}
