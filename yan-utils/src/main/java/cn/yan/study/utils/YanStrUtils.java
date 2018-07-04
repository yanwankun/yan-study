package cn.yan.study.utils;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/25
 * Time  19:28
 */
public class YanStrUtils {
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

    public static String getYanString(String prefix) {
        StringBuffer sb = new StringBuffer();
        sb.append(prefix);
        sb.append(YanDateUtils.getDateString(YanDateUtils.TIME_STRING_3));
        sb.append(YanStrUtils.getRandomCode(6));
        return sb.toString();
    }

    // todo
    private static String getRandomCode(int i) {
        return null;
    }
}
