package cn.yan.study.utils.email;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/26
 * Time  17:48
 */
public class Config {
    private static boolean mailSmtpAuth = true;

    public static String getMailSmtpUsername() {
        return "yanwankunde@qq.com";
    }

    public static String getMailSmtpHost() {
        return "smtp.qq.com";
    }

    public static String getMailSmtpPassword() {
        return "************";
    }

    public static boolean getMailSmtpAuth() {
        return mailSmtpAuth;
    }

    public static String getMailSmtpPort() {
        return "587";
    }
}
