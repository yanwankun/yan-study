package cn.yan.study.websocket.mychat.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-03-02
 */
@TableName("tb_my_chat_msg")
public class MyChatMsg extends Model<MyChatMsg> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 发送者id
     */
    @TableField("sender_id")
    private Long senderId;

    /**
     * 发送者昵称
     */
    @TableField("sender_nick_name")
    private String senderNickName;

    /**
     * 接收者id
     */
    @TableField("receiver_id")
    private Long receiverId;

    /**
     * 接收者昵称
     */
    @TableField("receiver_nick_name")
    private String receiverNickName;

    /**
     * 消息详情
     */
    @TableField("msg_info")
    private String msgInfo;

    /**
     * 消息发送时间
     */
    @TableField("send_time")
    private Date sendTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
    public String getSenderNickName() {
        return senderNickName;
    }

    public void setSenderNickName(String senderNickName) {
        this.senderNickName = senderNickName;
    }
    public Long getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Long receiverId) {
        this.receiverId = receiverId;
    }
    public String getReceiverNickName() {
        return receiverNickName;
    }

    public void setReceiverNickName(String receiverNickName) {
        this.receiverNickName = receiverNickName;
    }
    public String getMsgInfo() {
        return msgInfo;
    }

    public void setMsgInfo(String msgInfo) {
        this.msgInfo = msgInfo;
    }
    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MyChatMsg{" +
        "id=" + id +
        ", senderId=" + senderId +
        ", senderNickName=" + senderNickName +
        ", receiverId=" + receiverId +
        ", receiverNickName=" + receiverNickName +
        ", msgInfo=" + msgInfo +
        ", sendTime=" + sendTime +
        "}";
    }
}
