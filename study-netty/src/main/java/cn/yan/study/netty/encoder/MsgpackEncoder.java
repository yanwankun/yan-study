package cn.yan.study.netty.encoder;

import com.google.common.base.Throwables;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/

public class MsgpackEncoder extends MessageToByteEncoder<Object> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        //负责将POJO对象编码为byte数组
        MessagePack msgpack = new MessagePack();
        byte[] raw = null;
        try {
            raw = msgpack.write(msg);
        } catch (Exception e) {
            e.printStackTrace();
            Throwables.propagateIfPossible(e);
        }
        out.writeBytes(raw);
    }
}
