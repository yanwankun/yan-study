package cn.yan.study.springboot.domain;

import cn.yan.study.springboot.bean.UserInfoDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/** 用户群
 * Created by gentlemen_yan on 2019/3/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Crowd {
    private String crowdName;
    private Integer count;
    private Integer onLineCount;
    /**
     * 群主
     */
    private long userId;
    private long crowdId;
    /**
     * 管理员列表
     */
    private List<UserInfoDto> managementList;
}
