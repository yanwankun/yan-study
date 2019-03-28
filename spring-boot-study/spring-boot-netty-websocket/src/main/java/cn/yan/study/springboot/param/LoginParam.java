package cn.yan.study.springboot.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginParam {
    private String loginName;
    private String passWd;
}
