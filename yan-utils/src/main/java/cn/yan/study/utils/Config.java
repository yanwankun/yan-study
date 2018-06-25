package cn.yan.study.utils;


/**
 * 
 * 
 * @author qibin
 * @version v 0.1 2018年5月17日 下午5:54:24 qibin Exp $
 */
public class Config {

    /**
     * 邮箱相关的
     */
    private static final String MAIL_SMTP_HOST_KEY = "mail.smtp.host";
    private static final String MAIL_SMTP_PORT_KEY = "mail.smtp.port";
    private static final String MAIL_SMTP_USERNAME_KEY = "mail.smtp.username";
    private static final String MAIL_SMTP_PASSWORD_KEY = "mail.smtp.password";
    private static final String MAIL_SMTP_AUTH_KEY = "mail.smtp.auth";
    private static final String MAIL_REALLY_SEND = "mail.really.send";
    private static final String VERIFY_CODE_REALLY_VERIFY = "verify.code.really.verify";

    /**
     * 消息跳转地址
     */
    private static final String MSG_TARGET_URL_PREFIX = "msg.target.url.";

    /**
     * 消息阅读状态图标
     */
    private static final String MSG_UN_READ_IMG_URL = "msg.un.read.img.url";
    private static final String MSG_HAVE_READ_IMG_URL = "msg.have.read.img.url";

    /**
     * 消息默认发送id
     */
    private static final String DEFAULT_SEND_ID = "msg.default.send.id";

    /**
     * 邮件发送频率设置
     */
    private static final String EMAIL_SEND_LIMITED_TIME_MINUTE = "email.send.limited.time.minute";
    private static final String EMAIL_SEND_LIMITED_TIME_MAX_COUNT = "email.send.limited.time.max.count";

    /**
     * 消息处理器支持的code类型
     */
    private static final String DEFAULT_MSG_PROCESSOR_SUPPORT_TEMPLATE_CODES = "default.msg.processor.support.template.codes";
    private static final String EMAIL_MSG_PROCESSOR_SUPPORT_TEMPLATE_CODES = "email.msg.processor.support.template.codes";

    public static String getDefaultMsgProcessorSupportTemplateCodes() {
        return FundamentalConfigProvider.getString(DEFAULT_MSG_PROCESSOR_SUPPORT_TEMPLATE_CODES);
    }

    public static String getEmailMsgProcessorSupportTemplateCodes() {
        return FundamentalConfigProvider.getString(EMAIL_MSG_PROCESSOR_SUPPORT_TEMPLATE_CODES);
    }

    public static Integer getEmailSendLimitedTimeMinute() {
        return FundamentalConfigProvider.getInt(EMAIL_SEND_LIMITED_TIME_MINUTE, 5);
    }

    public static Integer getEmailSendLimitedTimeMaxCount() {
        return FundamentalConfigProvider.getInt(EMAIL_SEND_LIMITED_TIME_MAX_COUNT, 10);
    }

    public static String getDefaultSendId() {
        return FundamentalConfigProvider.getString(DEFAULT_SEND_ID, "System");
    }

    public static String getMsgUnReadImgUrl() {
        return FundamentalConfigProvider.getString(MSG_UN_READ_IMG_URL, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529067392012&di=ca7e62ed34b0a7654152571a69ce4e86&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F89%2F36%2F86A58PICrva.jpg");
    }

    public static String getMsgHaveReadImgUrl() {
        return FundamentalConfigProvider.getString(MSG_HAVE_READ_IMG_URL, "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1529067392012&di=ca7e62ed34b0a7654152571a69ce4e86&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F11%2F89%2F36%2F86A58PICrva.jpg");
    }


    public static String getMailSmtpHost()
    {
        return FundamentalConfigProvider.getString(MAIL_SMTP_HOST_KEY);
    }

    public static Integer getMailSmtpPort()
    {
        return FundamentalConfigProvider.getInt(MAIL_SMTP_PORT_KEY);
    }

    public static String getMailSmtpUsername()
    {
        return FundamentalConfigProvider.getString(MAIL_SMTP_USERNAME_KEY);
    }

    public static String getMailSmtpPassword()
    {
        return FundamentalConfigProvider.getString(MAIL_SMTP_PASSWORD_KEY);
    }

    public static Boolean getMailSmtpAuth()
    {
        return FundamentalConfigProvider.getBoolean(MAIL_SMTP_AUTH_KEY);
    }

    public static String getMsgTargetUrl(String templateCode) {
        return FundamentalConfigProvider.getString(MSG_TARGET_URL_PREFIX + templateCode.toLowerCase(), "need_get_targetUrl");
    }

    public static Boolean getMailReallySend() {
        return FundamentalConfigProvider.getBoolean(MAIL_REALLY_SEND, true);
    }

    /**
     * 获取验证码是否真的判断
     */
    public static Boolean getVerifyCodeReallyVerify() {
        return FundamentalConfigProvider.getBoolean(VERIFY_CODE_REALLY_VERIFY, false);
    }

    private static class FundamentalConfigProvider {
        public static String getString(String msgHaveReadImgUrl, String s) {
            return null;
        }

        public static Boolean getBoolean(String verifyCodeReallyVerify, boolean b) {
            return Boolean.FALSE;
        }

        public static Integer getInt(String emailSendLimitedTimeMinute, int i) {
            return null;
        }

        public static String getString(String mailSmtpHostKey) {
            return null;
        }

        public static Integer getInt(String mailSmtpPortKey) {
            return null;
        }

        public static Boolean getBoolean(String mailSmtpAuthKey) {
            return null;
        }
    }
}
