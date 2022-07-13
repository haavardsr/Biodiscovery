package utils;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * a class for generating CSRF-token
 * */
public class CSRF {


    /**
     * A method for generating and returning a CSRF-token
     * */
    public synchronized static String getToken() throws NoSuchAlgorithmException, EncoderException {
        // generate random bytes
        SecureRandom secureRandom = new SecureRandom();
        byte[] data = new byte[512];
        secureRandom.nextBytes(data);

        // convert to Base64 string
        String csrf = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] csrfBytes = md.digest(data);
            Base64 b64 = new Base64();
            return new String(b64.encode(csrfBytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, EncoderException {

        // test
        System.out.println(CSRF.getToken());
    }
}
