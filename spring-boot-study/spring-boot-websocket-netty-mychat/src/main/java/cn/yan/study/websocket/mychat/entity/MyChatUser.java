package cn.yan.study.websocket.mychat.entity;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mht
 * @since 2019-03-02
 */
@TableName("tb_my_chat_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyChatUser extends Model<MyChatUser> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 昵称
     */
    @TableField("nick_name")
    private String nickName;

    /**
     * 性别
     */
    private Boolean sex;

    @TableField("create_time")
    private Date createTime;

    @TableField("update_time")
    private Date updateTime;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "MyChatUser{" +
        "id=" + id +
        ", nickName=" + nickName +
        ", sex=" + sex +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
