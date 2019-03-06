package cn.yan.study.websocket.mychat.controller;

import cn.yan.study.websocket.mychat.common.utils.SendUtil;
import cn.yan.study.websocket.mychat.redis.LikeRedisTemplate;
import cn.yan.study.websocket.mychat.result.ApiResult;
import io.netty.channel.Channel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gentlemen_yan on 2019/3/2.
 */
@RestController
@RequestMapping("/back")
public class MsgController {

    @Autowired
    private LikeRedisTemplate likeRedisTemplate;


    @PostMapping("login")
    public ApiResult logIn(@RequestParam String sender, @RequestParam String logSign) {
        ApiResult result = new ApiResult();

        return result;
    }

    @PostMapping("/send")
    public ApiResult send(@RequestParam String sender, @RequestParam String receiver, @RequestParam String msg){
        ApiResult result = new ApiResult();
        Channel channel = (Channel) likeRedisTemplate.getChannel(receiver);
        if (channel == null){
            return result.makeFailedResult("当前用户不在线");
        }
        String sendResult = SendUtil.sendTest(msg,channel);
        return result.makeSuccessResult(sendResult);
    }
}
