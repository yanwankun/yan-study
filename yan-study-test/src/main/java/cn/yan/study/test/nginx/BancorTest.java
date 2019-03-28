package cn.yan.study.test.nginx;

/**
 * Created by gentlemen_yan on 2019/3/24.
 */
public class BancorTest {
    private static double supply = 1000;
    private static double price = 0.5;
    private static double cw = 0.5;
    private static double totalValue = 250;


    public static double testBuy(double payValue) {
        return supply * (Math.pow((1 + payValue/ totalValue), cw) - 1);
    }

    public static void main(String[] args) {
        System.out.println(testBuy(750));
        System.out.println(sell(1000));
    }

    public static double sell(double payValue) {
        return totalValue * (1 - Math.pow((1 - payValue/supply), 1 / cw));
    }
}
