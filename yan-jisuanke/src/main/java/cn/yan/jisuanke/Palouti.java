package cn.yan.jisuanke;
/**
 * Created by gentlemen_yan on 2018/12/10.
 */
public class Palouti {

    public static int getfenzi(int v1, int v2) {
        if (v1 == v2) return v1;
        return v1 * getfenzi(v1-1, v2);
    }

    public static int getjisehng(int v1) {
        if (v1 == 1) return 1;
        return v1 * getjisehng(v1-1);
    }
    public static int getZuhe(int v1, int v2) {
        return getfenzi(2,2);
    }
    public static void main(String[] args) {

    }
}
