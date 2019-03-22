package cn.yan.study.springboot.websocket.adapter;

import cn.yan.study.springboot.websocket.service.WebSocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
@Component
public class WebSocketAdapter {

    @Autowired
    private WebSocketService webSocketService;

//    public String processMsg(String msg, String sessionId) {
//        RequestParam param = JSON.parseObject(msg, RequestParam.class);
//        if (null == param) {
//            return null;
//        }
//
//        int index = Config.getMethodIndex(param.getMethodName());
//        if (index == -1) {
//            return null;
//        }
//
//        Object data = null;
//        switch (index) {
//            case 0 :
//                KLineRequestParam param1 = JSON.parseObject(param.getParams(), KLineRequestParam.class);
//                if (null == param1) {
//                    return null;
//                }
//                data = webSocketService.getBars(param1);
//                break;
//            case 1 :
//                SubscribeBarRequestParam param2 = JSON.parseObject(param.getParams(), SubscribeBarRequestParam.class);
//                if (null == param2) {
//                    return null;
//                }
//                webSocketService.subscribeBars(param2, sessionId);
//                break;
//            case 2 :
//                SubscribeBarRequestParam param3 = JSON.parseObject(param.getParams(), SubscribeBarRequestParam.class);
//                if (null == param3) {
//                    return null;
//                }
//                webSocketService.unsubscribeBars(param3, sessionId);
//                break;
//            case 3 :
//                RealTimeQuoteRequestParam param4 = JSON.parseObject(param.getParams(), RealTimeQuoteRequestParam.class);
//                if (null == param4) {
//                    return null;
//                }
//                data = webSocketService.realTimeQuote(param4, sessionId);
//                break;
//            case 4 :
//                PendOrderRequestParam param5 = JSON.parseObject(param.getParams(), PendOrderRequestParam.class);
//                if (null == param5) {
//                    return null;
//                }
//                data = webSocketService.realTimePendingOrder(param5);
//                break;
//            case 5 :
//                PendOrderRequestParam param6 = JSON.parseObject(param.getParams(), PendOrderRequestParam.class);
//                if (null == param6) {
//                    return null;
//                }
//                data = webSocketService.realTimeClinch(param6);
//                break;
//            case 6:
//                PendOrderRequestParam param7 = JSON.parseObject(param.getParams(), PendOrderRequestParam.class);
//                webSocketService.cancelMsgOrder(param7, sessionId);
//                break;
//            case 7:
//                ResolveSymbolRequestParam param8 = JSON.parseObject(param.getParams(), ResolveSymbolRequestParam.class);
//                data = webSocketService.resolveSymbol(param8);
//                break;
//            default: return null;
//        }
//
//        if (null != data) {
//            MsgDataResult msgDataResult = MsgDataResult.builder()
//                    .data(data)
//                    .methodName(param.getMethodName())
//                    .build();
//            return JSON.toJSONString(msgDataResult);
//        }
//
//        return null;
//    }

}
