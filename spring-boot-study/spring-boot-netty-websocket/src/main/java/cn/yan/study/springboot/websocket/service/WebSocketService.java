package cn.yan.study.springboot.websocket.service;

import cn.yan.study.springboot.websocket.param.LoginParam;
import cn.yan.study.springboot.websocket.result.BaseResult;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public interface WebSocketService {

    /**
     * 登录
     * @param param
     * @return
     */
    BaseResult login(LoginParam param);


}
