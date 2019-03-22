package cn.yan.study.springboot.websocket;

import cn.yan.study.springboot.netty.annotation.*;
import cn.yan.study.springboot.netty.pojo.ParameterMap;
import cn.yan.study.springboot.netty.pojo.Session;
import cn.yan.study.springboot.utils.YanStringUtils;
import cn.yan.study.springboot.websocket.store.WebSocketStoreUtils;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.timeout.IdleStateEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@ServerEndpoint(prefix = "netty_web_socket")
@Component
public class MyWebSocket {

    /**
     * 当前在线总人数
     */
    private static int onlineCount = 0;

    private static String token_key = "token";

    /**
     * 建立连接并保存用户channel, 返回用户token
     * @param session
     * @param headers
     * @param parameterMap
     * @throws IOException
     */
    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        String token = YanStringUtils.getRandomStr(20);
        while (WebSocketStoreUtils.tokenIsUse(token)) {
            token = YanStringUtils.getRandomStr(20);
        }
        session.setAttribute(token_key, token);
        WebSocketStoreUtils.addChannel(token, session.channel());
        addOnlineCount();
        session.sendText("connect is success, your token is :" + token);
    }

    /**
     * 关闭连接 删除所有保存的channel
     * @param session
     * @throws IOException
     */
    @OnClose
    public void onClose(Session session) throws IOException {
        subOnlineCount();
        WebSocketStoreUtils.reMoveChannel(session.getAttribute(token_key));
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        subOnlineCount();
        WebSocketStoreUtils.reMoveChannel(session.getAttribute(token_key));
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        System.out.println("your token is :" + session.getAttribute("token"));
        session.sendText("your message is :" + message);
    }

    @OnBinary
    public void onBinary(Session session, byte[] bytes) {
        for (byte b : bytes) {
            System.out.println(b);
        }
        session.sendBinary(bytes);
    }

    @OnEvent
    public void onEvent(Session session, Object evt) {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
            switch (idleStateEvent.state()) {
                case READER_IDLE:
                    System.out.println("read idle");
                    break;
                case WRITER_IDLE:
                    System.out.println("write idle");
                    break;
                case ALL_IDLE:
                    System.out.println("all idle");
                    break;
                default:
                    break;
            }
        }
    }


    public static synchronized void addOnlineCount() {
        MyWebSocket.onlineCount++;
    }

    public static synchronized void subOnlineCount() {
        MyWebSocket.onlineCount--;
    }

}
