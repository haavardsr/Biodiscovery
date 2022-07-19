package utils;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * A class for encrypting passwords based on sha256 algorithm
 * For making the password hash stronger, salt and pepper is used
 *
 * @author Haavard
 * @version 1.0
 */
public class SecureUtils {
    private final static String PEPPER = "12Hr!ksl_ww%123!";

    public static String getPEPPER() {
        return PEPPER;
    }

    /**
     * A method for hashing a password with salt and pepper
     *
     * @param password the plain text password
     * @param salt     Stringified array of bytes to combine with the password byte values
     * @return a string generatedPassword hash
     */
    public static String makePassword(String password, String salt) {
        byte[] saltBytes;
        Base64 b64 = new Base64();
        saltBytes = b64.decode(salt);
        System.out.println(Arrays.toString(saltBytes));
        byte[] bothSaltBytes = Arrays.copyOf(saltBytes, saltBytes.length + PEPPER.getBytes().length);
        System.arraycopy(PEPPER.getBytes(), 0, bothSaltBytes, saltBytes.length, PEPPER.getBytes().length);

        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(bothSaltBytes);
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }


    /**
     * A method for generating a random byte array as salt
     *
     * @return a stringified generated salt byte array
     */
    public static String makeSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.encodeBase64String(salt);
    }

    /**
     * main method for testing and generating password and salt
     */
    public static void main(String[] args) throws NoSuchAlgorithmException, EncoderException {

        /** Make salt for hashing two passwords*/
        String salt = makeSalt();
        /** Print the salt generated*/
        System.out.println(salt);
        String password1 = makePassword("12345", salt);
        String password2 = makePassword("12345", salt);

        /** Print out password hashes**/
        System.out.println(" Password 1 -> " + password1);
        System.out.println(" Password 2 -> " + password2);
        /** Test if passwords match*/
        if (password1.equals(password2)) {
            System.out.println("passwords are equal");
        }

    }
}

