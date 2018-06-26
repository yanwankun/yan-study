package cn.yan.study.utils.email;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

/**
 * Created
 * User  wankunYan
 * Date  2017/7/18
 * Time  19:29
 */
public class SendMailUtils {
    private static final Logger logger = LoggerFactory.getLogger(SendMailUtils.class);

    private static String KEY_SMTP = "mail.smtp.host";
    // 服务器验证
    private static String KEY_PROPS = "mail.smtp.auth";
    private static String KEY_PORT = "mail.smtp.port";
    private static boolean VALUE_PROPS = Config.getMailSmtpAuth();

    // 建立会话
    private MimeMessage message;
    private Session session;

    /*
     * 初始化方法
     */
    SendMailUtils() {
        Properties props = System.getProperties();
        props.setProperty(KEY_SMTP, Config.getMailSmtpHost());
        props.setProperty(KEY_PORT,Config.getMailSmtpPort());
        props.put(KEY_PROPS, VALUE_PROPS);
        session =  Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Config.getMailSmtpUsername(), Config.getMailSmtpPassword());
            }});
        session.setDebug(true);
        message = new MimeMessage(session);
    }

    /**
     * 发送邮件
     *
     * @param subject
     *            邮件头文件名
     * @param sendHtml
     *            邮件内容
     * @param receiveAddress
     *            收件人地址
     */
    boolean doSendHtmlEmail(String subject, String sendHtml,
                                   String receiveAddress) {
        try {
            InternetAddress from = new InternetAddress(Config.getMailSmtpUsername());
            message.setFrom(from);
            InternetAddress to = new InternetAddress(receiveAddress);
            message.setRecipient(Message.RecipientType.TO, to);
            message.setSubject(subject);
            message.setContent(sendHtml, "text/html;charset=UTF8");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(Config.getMailSmtpHost(), Config.getMailSmtpUsername(), Config.getMailSmtpPassword());

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }
    boolean doSendHtmlEmail(String subject, String sendHtml,
                                   List<String> receiveAddressList) {
        try {
            InternetAddress from = new InternetAddress(Config.getMailSmtpUsername());
            message.setFrom(from);
            final int num = receiveAddressList.size();
            InternetAddress[] addresses = new InternetAddress[num];
            for (int i = 0; i <num; i++) {
                addresses[i] = new InternetAddress(receiveAddressList.get(i));
            }
            message.setRecipients(Message.RecipientType.TO, addresses);
            message.setSubject(subject);
            message.setContent(sendHtml, "text/html;charset=UTF8");
            message.saveChanges();
            Transport transport = session.getTransport("smtp");
            transport.connect(Config.getMailSmtpHost(), Config.getMailSmtpUsername(), Config.getMailSmtpPassword());

            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            return true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return false;
    }


    public static void main(String[] args) {
        SendMailUtils se = new SendMailUtils();
        boolean bool = se.doSendHtmlEmail("", "tttttt", "yanwankunde@sina.com");
        System.out.println(bool);
    }

}
