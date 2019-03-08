//package cn.yan.study.test.nginx;
//
//import java.net.URI;
//import java.security.cert.CertificateException;
//import java.security.cert.X509Certificate;
//
//import javax.net.ssl.HostnameVerifier;
//import javax.net.ssl.SSLContext;
//import javax.net.ssl.SSLSession;
//import javax.net.ssl.TrustManager;
//import javax.net.ssl.X509TrustManager;
//
//import org.java_websocket.client.WebSocketClient;
//import org.java_websocket.drafts.Draft;
//
///**
// * Created with IDEA
// *
// * @author: gentlemen_k
// * @emali: test@qq.com
// **/
//
//abstract class SimpleWssTest extends WebSocketClient {
//
//    public SimpleWss(URI serverURI) {
//        super(serverURI);
//        if(serverURI.toString().contains("wss://"))
//            trustAllHosts(this);
//    }
//
//    public SimpleWss(URI serverURI,Draft draft) {
//        super(serverURI,draft);
//        if(serverURI.toString().contains("wss://"))
//            trustAllHosts(this);
//    }
//
//    final static HostnameVerifier DO_NOT_VERIFY = new HostnameVerifier() {
//        public boolean verify(String hostname, SSLSession session) {
//            return true;
//        }
//    };
//
//
//    static void trustAllHosts(SimpleWss appClient) {
//        System.out.println("wss");
//        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
//            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
//                return new java.security.cert.X509Certificate[]{};
//            }
//
//            @Override
//            public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
//                // TODO Auto-generated method stub
//
//            }
//        }};
//
//        try {
//            SSLContext sc = SSLContext.getInstance("TLS");
//            sc.init(null, trustAllCerts, new java.security.SecureRandom());
//            appClient.setWebSocketFactory(new DefaultSSLWebSocketClientFactory(sc));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
