package cn.yan.study.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 消息
 * Created by gentlemen_yan on 2019/3/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    /**
     * 消息id
     */
    private long id;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 消息发送时间
     */
    private Date sendTime;
    /**
     * 发送者
     */
    private long senderId;
    /**
     * 接受者
     */
    private long receiveId;
    /**
     * 接收者类型
     */
    private int receiveType;
}
