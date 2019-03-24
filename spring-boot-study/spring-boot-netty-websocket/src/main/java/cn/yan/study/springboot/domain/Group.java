package cn.yan.study.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个类似于qq里面得好友分组
 * Created by gentlemen_yan on 2019/3/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Group {
    private String groupName;
    private Integer count;
    private Integer onLineCount;
    private long userId;
    private long groupId;
}
