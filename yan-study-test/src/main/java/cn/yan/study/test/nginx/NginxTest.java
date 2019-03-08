package cn.yan.study.test.nginx;

import java.net.Socket;
import java.util.concurrent.CountDownLatch;

/**
 * Created by gentlemen_yan on 2019/2/28.
 */
public class NginxTest {

    private static CountDownLatch cdl = new CountDownLatch(10000);
    private static long hour = 60 * 60 * 1000L;


    public static void main(String[] args) {
        for (int i = 0; i < 10000; i++) {
            ClientThread clientThread = new ClientThread();
            new Thread(clientThread).start();
            cdl.countDown();
            System.out.println(i);
        }
    }

    static class ClientThread implements Runnable{

        public void run() {
            try {
                cdl.await();

                Socket socket = new Socket("118.24.150.85", 80);
                System.out.println(Thread.currentThread().getName() + "is runing");
                Thread.sleep(hour);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
