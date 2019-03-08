import cn.yan.study.websocket.mychat.ProjectApplication;
import cn.yan.study.websocket.mychat.entity.MyChatUser;
import cn.yan.study.websocket.mychat.service.IMyChatUserService;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JsonbTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProjectApplication.class)
public class UserServiceTest {

    @Autowired
    private IMyChatUserService iMyChatUserService;
    @Test
    public void testAdd() {
        iMyChatUserService.insert(MyChatUser.builder()
                .nickName("yanwankun")
                .sex(Boolean.TRUE)
                .createTime(new Date())
                .updateTime(new Date())
                .build());
    }

    @Test
    public void testSelect() {
        MyChatUser existUser = iMyChatUserService.selectById(MyChatUser.builder().id(1L).build());
        System.out.println(JSON.toJSONString(existUser));
    }
}
