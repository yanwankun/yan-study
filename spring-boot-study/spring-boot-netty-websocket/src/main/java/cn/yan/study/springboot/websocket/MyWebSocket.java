package cn.yan.study.springboot.websocket;

import cn.yan.study.springboot.websocket.annotation.*;
import cn.yan.study.springboot.websocket.pojo.ParameterMap;
import cn.yan.study.springboot.websocket.pojo.Session;
import cn.yan.study.springboot.websocket.utils.ConcurrentHashMapCacheUtils;
import com.alibaba.fastjson.JSON;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
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

    @OnOpen
    public void onOpen(Session session, HttpHeaders headers, ParameterMap parameterMap) throws IOException {
        System.out.println("new connection");

        String paramValue = parameterMap.getParameter("paramKey");
        System.out.println(paramValue);
        System.out.println("******************************");
        System.out.println(JSON.toJSONString(session));

        session.sendText("connect is success (send by session)");
        session.setAttribute("token", "tokenA");
        ConcurrentHashMapCacheUtils.setCache(session.getAttribute("token"), session.getChannel());

        Channel channel = (Channel)ConcurrentHashMapCacheUtils.getCache(session.getAttribute("token"));

        channel.writeAndFlush(new TextWebSocketFrame("connect is success (send by cache)"));
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        System.out.println("one connection closed");
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        System.out.println(message);
        System.out.println("your token is :" + session.getAttribute("token"));
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

}
