//package cn.yan.study.netty.test;
//
///**
// * Created with IDEA
// *
// * @author: gentlemen_k
// * @emali: test@qq.com
// **/
//import com.google.protobuf.InvalidProtocolBufferException;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author Administrator
// * @version 1.0
// * @date 2014年2月23日
// */
//public class TestSubscribeReqProto {
//
//    // 编码方法: Object->byte[]
//    private static byte[] encode(SubscribeReqProto.SubscribeReq req) {
//        return req.toByteArray();
//    }
//
//    // 解码方法： bayte[] -> Object
//    private static SubscribeReqProto.SubscribeReq decode(byte[] body)
//            throws InvalidProtocolBufferException {
//        return SubscribeReqProto.SubscribeReq.parseFrom(body);
//    }
//
//    /**
//     * 创建实例
//     *
//     * @return
//     */
//    private static SubscribeReqProto.SubscribeReq createSubscribeReq() {
//        //(1) Builder模式
//        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq
//                .newBuilder();
//        builder.setSubReqID(1);
//        builder.setUserName("Lilinfeng");
//        builder.setProductName("Netty Book");
//        List<String> address = new ArrayList<>();
//        address.add("NanJing YuHuaTai");
//        address.add("BeiJing LiuLiChang");
//        address.add("ShenZhen HongShuLin");
//        builder.addAllAddress(address);
//        return builder.build();
//    }
//
//    /**
//     * @param args
//     * @throws InvalidProtocolBufferException
//     */
//    public static void main(String[] args)
//            throws InvalidProtocolBufferException {
//        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
//        System.out.println("Before encode : " + req.toString());
//        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));
//        System.out.println("After decode : " + req.toString());
//        System.out.println("Assert equal : --> " + req2.equals(req));
//
//    }
//
//}
