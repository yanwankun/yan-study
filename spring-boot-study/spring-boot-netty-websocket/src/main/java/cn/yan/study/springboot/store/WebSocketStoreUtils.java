package cn.yan.study.springboot.store;

import cn.yan.study.springboot.netty.bean.TokenInfo;
import io.netty.channel.Channel;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class WebSocketStoreUtils {

    private static Map<String, TokenInfo> tokenInfoMap = new ConcurrentHashMap<>();
    /**
     * 这个是记录所有用户的token和对应的channel
     */
    private static Map<String, Channel> tokenChannelMap = new ConcurrentHashMap<>();

    /**
     * k线数据订阅 时间段 交易对 tokenList
     */
    private static Map<String, Map<String, List<String>>> kLineOrderMap = new ConcurrentHashMap<>();

    /**
     * 数据消息订阅
     */
    private static Map<String, List<String>> dataOrderMap = new ConcurrentHashMap<>();

//    /**
//     *
//     * @param param
//     * @param token
//     */
//    public static void subscribeBars(SubscribeBarRequestParam param, String token) {
//        Map<String, List<String>> kLineMap =
//                kLineOrderMap.computeIfAbsent(param.getResolution(), k -> new HashMap<>());
//        List<String> tokenList =
//                kLineMap.computeIfAbsent(param.getSymbolInfo().getString(), k -> new LinkedList<>());
//
//        tokenList.add(token);
//
//        TokenInfo tokenInfo = new TokenInfo(Boolean.TRUE, param.getResolution(), param.getSymbolInfo().getString());
//        tokenInfoMap.put(token, tokenInfo);
//    }
//
//    /**
//     * 取消K线图数据订阅
//     */
//    public static void unsubscribeBars(SubscribeBarRequestParam param, String token) {
//        Map<String, List<String>> kLineMap =
//                kLineOrderMap.computeIfAbsent(param.getResolution(), k -> new HashMap<>());
//        List<String> tokenList =
//                kLineMap.computeIfAbsent(param.getSymbolInfo().getString(), k -> new LinkedList<>());
//        tokenList.remove(token);
//        tokenInfoMap.remove(token);
//    }
//
//    /**
//     * 添加数据消息订阅
//     */
//    public static void addDataOrder(TradePair tradePair, String token) {
//        List<String> tokenList =
//                dataOrderMap.computeIfAbsent(tradePair.getString(), k -> new LinkedList<>());
//            tokenList.add(token);
//        TokenInfo tokenInfo = new TokenInfo(Boolean.TRUE, "", tradePair.getString());
//        tokenInfoMap.put(token, tokenInfo);
//    }

    /**
     * 获取数据推送列表
     * @param tradeStr
     * @return
     */
    public static List<Channel> getDataPushList(String tradeStr) {
        List<Channel> dataList = new ArrayList<>();
        List<String> tokenList =
                dataOrderMap.computeIfAbsent(tradeStr, k -> new LinkedList<>());
        if (tokenList.isEmpty()) {
            return dataList;
        }

        for (String token : tokenList) {
            Channel channel = tokenChannelMap.get(token);
            if (channel != null) {
                dataList.add(channel);
            }
        }
        return dataList;
    }

    /**
     * 根据订阅的区段时间和交易对,获取需要推送的channel列表
     * @param tradeStr
     * @param solution
     * @return
     */
    public static List<Channel> getKLineChanel(String tradeStr, String solution) {
        List<Channel> dataList = new ArrayList<>();
        Map<String, List<String>> kLineMap =
                kLineOrderMap.computeIfAbsent(solution, k -> new HashMap<>());
        List<String> tokenList =
                kLineMap.computeIfAbsent(tradeStr, k -> new LinkedList<>());
        if (tokenList.isEmpty()) {
            return dataList;
        }

        for (String token : tokenList) {
            Channel channel = tokenChannelMap.get(token);
            if (channel != null) {
                dataList.add(channel);
            }
        }
        return dataList;
    }

    public static boolean tokenIsUse(String token) {
        return tokenChannelMap.containsKey(token);
    }

    public static void addChannel(String token, Channel channel) {
        tokenChannelMap.put(token, channel);
    }

    public static void reMoveChannel(String token) {
        if (token == null) {
            return;
        }
        if (tokenInfoMap.containsKey(token)) {
            TokenInfo tokenInfo = tokenInfoMap.get(token);
            if (tokenInfo.getKLine()) {
                Map<String, List<String>> kLineMap =
                        kLineOrderMap.computeIfAbsent(tokenInfo.getSolution(), k -> new HashMap<>());
                List<String> tokenList =
                        kLineMap.computeIfAbsent(tokenInfo.getTradeString(), k -> new LinkedList<>());
                tokenList.remove(token);
                tokenInfoMap.remove(token);
            } else {
                List<String> tokenList =
                        dataOrderMap.computeIfAbsent(tokenInfo.getTradeString(), k -> new LinkedList<>());
                tokenList.remove(token);
            }
            tokenInfoMap.remove(token);
        }
        tokenChannelMap.remove(token);
    }


}


