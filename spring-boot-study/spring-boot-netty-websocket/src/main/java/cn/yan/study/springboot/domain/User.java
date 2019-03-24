package cn.yan.study.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/** 用户信息
 * Created by gentlemen_yan on 2019/3/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private long id;
    private String loginName;
    private String nickName;
    private String passWd;
    private String salt;
    private Date registerTime;
    private Date lastLoginTime;
    private Date logoutTime;
}
