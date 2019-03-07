package cn.yan.study.netty.coding;


import com.google.common.collect.Lists;
import org.msgpack.MessagePack;
import org.msgpack.template.Templates;
import java.util.List;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class ApiDemo {
    public static void main(String[] args) throws Exception {
        //使用了guava
        List<String> src = Lists.newArrayList("msgpack", "kumofs", "viver");
        MessagePack msgpack = new MessagePack();
        //序列化
        byte[] raw = msgpack.write(src);
        //反序列化
        List<String> dst1 = msgpack.read(raw, Templates.tList(Templates.TString));
        System.out.println(dst1);
    }
}
