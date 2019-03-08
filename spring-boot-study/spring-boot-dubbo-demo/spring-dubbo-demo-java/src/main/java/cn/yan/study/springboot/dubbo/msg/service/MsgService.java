package cn.yan.study.springboot.dubbo.msg.service;

import cn.yan.study.springboot.dubbo.msg.param.SendMsgParam;
import cn.yan.study.springboot.dubbo.msg.result.MsgSendResult;

/**
 * Created by gentlemen_yan on 2019/3/3.
 */
public interface MsgService {
    MsgSendResult msgSend(SendMsgParam param);
}
