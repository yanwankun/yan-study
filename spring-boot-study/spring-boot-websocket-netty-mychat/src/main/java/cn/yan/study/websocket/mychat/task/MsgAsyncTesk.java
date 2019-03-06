package cn.yan.study.websocket.mychat.task;

import cn.yan.study.websocket.mychat.entity.MyChatMsg;
import cn.yan.study.websocket.mychat.entity.MyChatUser;
import cn.yan.study.websocket.mychat.redis.LikeSomeCacheTemplate;
import cn.yan.study.websocket.mychat.service.IMyChatMsgService;
import cn.yan.study.websocket.mychat.service.IMyChatUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 13:50 2018\8\14 0014
 */
@Component
public class MsgAsyncTesk {

    @Autowired
    private LikeSomeCacheTemplate cacheTemplate;

//    @Autowired
//    private IMyChatUserService iMyChatUserService;

    @Autowired
    private IMyChatMsgService iMyChatMsgService;

    @Async
    public Future<Boolean> saveChatMsgTask() throws Exception{

        List<MyChatMsg> userMsgList = cacheTemplate.cloneCacheMap();
        for (MyChatMsg msg : userMsgList){
            iMyChatMsgService.insert(msg);
        }
        //清空临时缓存
        cacheTemplate.clearCacheMap();
        return new AsyncResult<>(true);
    }

}
