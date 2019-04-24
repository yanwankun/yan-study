package cn.yan.study.utils;

/**
 * Created by gentlemen_yan on 2019/3/30.
 */
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA256 {
    private final static String MESSAGE_DIGEST_ALGORITHM = "SHA-256";

    public static final synchronized String getMessageDigest(byte[] data) throws NoSuchAlgorithmException {
        MessageDigest digest = null;
        if(digest == null)
            try{
                digest = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
            }
            catch(NoSuchAlgorithmException nosuchalgorithmexception)
            {
                System.err.println("Failed to load the MD5 MessageDigest. system will be unable to function normally.");
                nosuchalgorithmexception.printStackTrace();
            }
        digest.reset();
        digest.update(data);
        return encodeHex(digest.digest());

    }

    public static final String encodeHex(byte abyte0[])
    {
        StringBuffer stringbuffer = new StringBuffer(abyte0.length * 2);
        for(int i = 0; i < abyte0.length; i++)
        {
            if((abyte0[i] & 0xff) < 16)
                stringbuffer.append("0");
            stringbuffer.append(Long.toString(abyte0[i] & 0xff, 16));
        }

        return stringbuffer.toString();
    }

    /**
     * [root@izj6c28ftc1v9t3nkz3cofz gentelmen123]# echo -n 'bf85cd98ee4c178a1debd8f78c322ece83f6648bf2af4de1d8eabd3e58c25703' | xxd -r -p | sha256sum -b | awk '{print $1}'
     e148bc73bc7ce6d94f84325a3cdadb089815671caaf3690b969811916ae8f318
     */
    public static String source = "76752b817e39a7e7e7b2c3cd30ce4f5600ff6261b336dd8beaea012c1a6bc208";
    public static String dest = "39c62369d1466174995d08cb1bd37b2e066302510a77ee3a6d3d63752b59411e";
    public static void main(String[] args){
        try{
            String svalue = source;
            System.out.println(getMessageDigest(svalue.getBytes("UTF-8")));
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
