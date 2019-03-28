package cn.yan.study.springboot.param;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by gentlemen_yan on 2019/3/23.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterParam {
    private String nickName;
    private String passWd;
}
