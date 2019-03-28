package cn.yan.study.test.nginx;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/
public class SingleUtils {

    private SingleUtils(){}

    private static volatile SingleUtils singleUtils;

    public static SingleUtils getInstance() {
        if (singleUtils == null) {
            synchronized (SingleUtils.class) {
                if (singleUtils == null) {
                    singleUtils = new SingleUtils();
                }
            }
        }

        return singleUtils;
    }

    public static void main(String[] args) {
        test(null, null);
    }

    public static void test(String var1, String var2) {
        System.out.println(var1 + var2);
    }
}
