//package cn.yan.study.springboot.websocket.adapter;
//
//import org.prophet.exchange.websocket.config.Config;
//import org.prophet.exchange.websocket.dto.KLineDto;
//import org.prophet.exchange.websocket.param.KLineRequestParam;
//import org.prophet.exchange.websocket.service.InnerService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * Created with IDEA
// *
// * @author: gentlemen_k
// * @emali: test@qq.com
// **/
//@Component
//public class InnerServiceAdapter {
//
//    @Autowired
//    private List<InnerService> innerServiceList;
//
//
//    public KLineDto getBars(KLineRequestParam param) {
//
//        int index = Config.getResolutionIndex(param.getResolution());
//        if (index == -1) {
//            return null;
//        }
//
//        InnerService service = null;
//        for (InnerService innerService : innerServiceList) {
//            if (innerService.getResolution().equals(param.getResolution())) {
//                service = innerService;
//                break;
//            }
//        }
//
//        if (service == null) {
//            return null;
//        }
//
//        param.setFrom(param.getFrom() * 1000L);
//        param.setTo(param.getTo() * 1000L);
//        return service.getBars(param);
//    }
//
//}
