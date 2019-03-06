package cn.yan.study.websocket.mychat.redis;

import cn.yan.study.websocket.mychat.entity.MyChatMsg;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * 聊天内容临时存储
 * @Author:UncleCatMySelf
 * @Email：zhupeijie_java@126.com
 * @QQ:1341933031
 * @Date:Created in 13:38 2018\8\14 0014
 */
@Component
public class LikeSomeCacheTemplate {

    private List<MyChatMsg> SomeCache = new LinkedList<>();

    public void save(MyChatMsg msg){
        SomeCache.add(msg);
    }

    public List<MyChatMsg> cloneCacheMap(){
        return SomeCache;
    }

    public void clearCacheMap(){
        SomeCache.clear();
    }
}
