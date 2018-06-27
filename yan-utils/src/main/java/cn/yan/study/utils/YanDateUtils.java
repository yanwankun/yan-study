package cn.yan.study.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/27
 * Time  17:00
 */
public class YanDateUtils {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
    public static final String TIME_STRING_1 = "yyyy-MM-dd";
    public static final String TIME_STRING_2 = "yyyy-MM-dd HH:mm:ss";

    public static Date parseDate(String dateString, String formatString) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatString);
        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }
}
