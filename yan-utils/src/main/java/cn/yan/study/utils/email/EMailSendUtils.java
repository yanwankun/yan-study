package cn.yan.study.utils.email;

import java.util.List;

/**
 * Created
 * User  wankunYan
 * Date  2018/6/5
 * Time  11:22
 */
public class EMailSendUtils {

//    @Autowired
//    private EmailSender emailSender;

    /**
     * 框架内邮件发送实现
     * @param headName
     * @param sendHtml
     * @param receiveAddressList
     * @return
     */
//    public boolean sendEmail(String headName, String sendHtml,
//                                    String receiveAddressList) {
//        emailSender.send(headName, receiveAddressList, Config.getMailSmtpUsername(), sendHtml);
//        return true;
//    }

    /**
     * 自己的邮件发送实现
     */
    public boolean sendEmailOld(String subject, String sendHtml,
                                    String receiveAddress) {
        return (new SendMailUtils()).doSendHtmlEmail(subject, sendHtml, receiveAddress);
    }

    public boolean sendEmailOld(String subject, String sendHtml,
                                List<String> receiveAddressList) {
        return (new SendMailUtils()).doSendHtmlEmail(subject, sendHtml, receiveAddressList);
    }

}
