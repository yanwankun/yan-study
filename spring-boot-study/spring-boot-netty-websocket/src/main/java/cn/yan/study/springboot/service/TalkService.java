package cn.yan.study.springboot.service;

import cn.yan.study.springboot.domain.Message;
import cn.yan.study.springboot.result.BaseResult;

/** 聊天服务
 * Created by gentlemen_yan on 2019/3/23.
 */
public interface TalkService {

    /**
     * 给一个人发送消息
     */
    BaseResult sendToOne(String token, Message msg);

    /**
     * 发送消息给一个组
     */
    BaseResult sendToGroup(long groupId, Message msg);

    /**
     * 发送消息给一个群
     */
    BaseResult sendToCrowd(long crowdId, Message msg);

}
