//package cn.yan.study.springboot.websocket.utils;
//
//import java.io.FileInputStream;
//import java.security.KeyStore;
//import java.security.PrivateKey;
//import java.security.PublicKey;
//import java.security.cert.Certificate;
//import java.util.Enumeration;
//
///**
// * Created with IDEA
// *
// * @author: gentlemen_k
// * @emali: test@qq.com
// **/
//
//public class ReadPFX {
//    public ReadPFX (){
//    }
//    //转换成十六进制字符串
//    public static String Byte2String(byte[] b) {
//        String hs="";
//        String stmp;
//
//        for (int n=0;n<b.length;n++) {
//            stmp=(java.lang.Integer.toHexString(b[n] & 0XFF));
//            if (stmp.length()==1) hs=hs+"0"+stmp;
//            else hs=hs+stmp;
//            //if (n<b.length-1)  hs=hs+":";
//        }
//        return hs.toUpperCase();
//    }
//
//    public static byte[] StringToByte(int number) {
//        int temp = number;
//        byte[] b=new byte[4];
//        for (int i=b.length-1;i>-1;i--){
//            b[i] = new Integer(temp&0xff).byteValue();//将最高位保存在最低位
//            temp = temp >> 8; //向右移8位
//        }
//        return b;
//    }
//    public  PrivateKey GetPvkformPfx(String strPfx, String strPassword){
//        try {
//            KeyStore ks = KeyStore.getInstance("PKCS12");
//            FileInputStream fis = new FileInputStream(strPfx);
//            // If the keystore password is empty(""), then we have to set
//            // to null, otherwise it won't work!!!
//            char[] nPassword = null;
//            if ((strPassword == null) || strPassword.trim().equals("")){
//                nPassword = null;
//            }
//            else
//            {
//                nPassword = strPassword.toCharArray();
//            }
//            ks.load(fis, nPassword);
//            fis.close();
//            System.out.println("keystore type=" + ks.getType());
//            Enumeration enumas = ks.aliases();
//            String keyAlias = null;
//            if (enumas.hasMoreElements())// we are readin just one certificate.
//            {
//                keyAlias = (String)enumas.nextElement();
//                System.out.println("alias=[" + keyAlias + "]");
//            }
//            System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
//            PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
//            Certificate cert = ks.getCertificate(keyAlias);
//            PublicKey pubkey = cert.getPublicKey();
//            System.out.println("cert class = " + cert.getClass().getName());
//            System.out.println("cert = " + cert);
//            System.out.println("public key = " + pubkey);
//            System.out.println("private key = " + prikey);
//            return prikey;
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//    public static void main(String[] args) throws Exception {
//        String strPfx = "G:\\workspaceOfJava\\javaSe_test\\testfile\\联想测试pfx证书-密码123.pfx";
//        String strPassword ="123";
//        KeyStore ks = KeyStore.getInstance("PKCS12");
//        FileInputStream fis = new FileInputStream(strPfx);
//        char[] nPassword = null;
//        if ((strPassword == null) || strPassword.trim().equals("")){
//            nPassword = null;
//        }
//        else
//        {
//            nPassword = strPassword.toCharArray();
//        }
//        ks.load(fis, nPassword);
//        fis.close();
//        System.out.println("keystore type=" + ks.getType());
//        // Now we loop all the aliases, we need the alias to get keys.
//        // It seems that this value is the "Friendly name" field in the
//        // detals tab <-- Certificate window <-- view <-- Certificate
//        // Button <-- Content tab <-- Internet Options <-- Tools menu
//        // In MS IE 6.
//        Enumeration enumas = ks.aliases();
//        String keyAlias = null;
//        if (enumas.hasMoreElements())// we are readin just one certificate.
//        {
//            keyAlias = (String)enumas.nextElement();
//            System.out.println("alias=[" + keyAlias + "]");
//        }
//        // Now once we know the alias, we could get the keys.
//        System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
//        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
//        Certificate cert = ks.getCertificate(keyAlias);
//        PublicKey pubkey = cert.getPublicKey();
//        System.out.println("cert class = " + cert.getClass().getName());
//        System.out.println("cert = " + cert);
//        System.out.println("public key = " + pubkey);
//        System.out.println("private key = " + prikey);
//    }
//}
