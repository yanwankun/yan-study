package cn.yan.study.springboot.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 这个是用户得简略信息
 * Created by gentlemen_yan on 2019/3/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDto {
    private String nickName;
    private String loginName;
    private String token;
}
