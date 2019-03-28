package cn.yan.study.test.nginx;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class Single2Utils {

    private Single2Utils(){}

    public static class Single2UtilsHold {
        public static Single2Utils singleUtils = new Single2Utils();
    }

    public static Single2Utils getInstance() {
        return Single2UtilsHold.singleUtils;
    }
}
