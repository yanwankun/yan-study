package cn.yan.study.test.nginx;

/**
 * Created with IDEA
 *
 * @author: gentlemen_k
 * @emali: test@qq.com
 **/

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyStore;

class WebSocketChatClient extends WebSocketClient {

    public WebSocketChatClient( URI serverUri ) {
        super( serverUri );
    }

    @Override
    public void onOpen( ServerHandshake handshakedata ) {
        System.out.println( "Connected" );

    }

    @Override
    public void onMessage( String message ) {
        System.out.println( "got: " + message );

    }

    @Override
    public void onClose( int code, String reason, boolean remote ) {
        System.out.println( "Disconnected" );

    }

    @Override
    public void onError( Exception ex ) {
        ex.printStackTrace();

    }

}

public class SSLClientExample {

    /*
     * Keystore with certificate created like so (in JKS format):
     *
     *keytool -genkey -keyalg RSA -validity 3650 -keystore "keystore.jks" -storepass "storepassword" -keypass "keypassword" -alias "default" -dname "CN=127.0.0.1, OU=MyOrgUnit, O=MyOrg, L=MyCity, S=MyRegion, C=MyCountry"
     */
    public static void main( String[] args ) throws Exception {
        WebSocketChatClient chatclient = new WebSocketChatClient( new URI( "wss://www.yanwankun.online:8443/websocket" ) );

        // load up the key store
        String STORETYPE = "JKS";
        String KEYSTORE = "D:\\aa_yan\\code\\exchange.jks";
        String STOREPASSWORD = "yanwankun";
        String KEYPASSWORD = "yanwankun";

        KeyStore ks = KeyStore.getInstance( STORETYPE );
        File kf = new File( KEYSTORE );
        System.out.println(kf.getAbsolutePath());
        ks.load( new FileInputStream( kf ), STOREPASSWORD.toCharArray() );

        KeyManagerFactory kmf = KeyManagerFactory.getInstance( "SunX509" );
        kmf.init( ks, KEYPASSWORD.toCharArray() );
        TrustManagerFactory tmf = TrustManagerFactory.getInstance( "SunX509" );
        tmf.init( ks );

        SSLContext sslContext = null;
        sslContext = SSLContext.getInstance( "TLS" );
        sslContext.init( kmf.getKeyManagers(), tmf.getTrustManagers(), null );
        // sslContext.init( null, null, null ); // will use java's default key and trust store which is sufficient unless you deal with self-signed certificates

        SSLSocketFactory factory = sslContext.getSocketFactory();// (SSLSocketFactory) SSLSocketFactory.getDefault();

        chatclient.setSocketFactory( factory );

        chatclient.connectBlocking();

        BufferedReader reader = new BufferedReader( new InputStreamReader( System.in ) );
        while ( true ) {
            String line = reader.readLine();
            if( line.equals( "close" ) ) {
                chatclient.closeBlocking();
            } else if ( line.equals( "open" ) ) {
                chatclient.reconnect();
            } else {
                chatclient.send( line );
            }
        }

    }
}
