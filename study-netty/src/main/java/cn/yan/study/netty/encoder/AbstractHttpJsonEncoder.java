package cn.yan.study.netty.encoder;

import com.alibaba.fastjson.JSON;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.charset.Charset;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public abstract class AbstractHttpJsonEncoder<T> extends MessageToMessageEncoder<T> {
    final static Charset UTF_8 = Charset.forName("utf-8");

    protected ByteBuf encode0(ChannelHandlerContext ctx, Object body) {
        String jsonStr = JSON.toJSONString(body);
        ByteBuf encodeBuf = Unpooled.copiedBuffer(jsonStr, UTF_8);
        return encodeBuf;
    }

}
