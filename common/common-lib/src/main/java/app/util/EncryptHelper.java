package app.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @author steve
 */
public class EncryptHelper {
    public static String encryptPassword(String originPassword, String salt, int iterated_times) throws PasswordEncryptException {
        MessageDigest digest;
        String algorithmName = "SHA-256";
        try {
            digest = MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncryptException(String.format("no such algorithm, name = %s", algorithmName), e);
        }
        digest.update(salt.getBytes());
        byte[] encryptedPasswordBytes = new byte[0];
        for (int i = 0; i < iterated_times; i++) {
            encryptedPasswordBytes = digest.digest(digest.digest(originPassword.getBytes()));
        }
        return new String(encryptedPasswordBytes, StandardCharsets.UTF_8);
    }

    public static String encryptSha1(List<String> list) throws EncryptException {
        String algorithmName = "SHA-1";
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            throw new EncryptException(String.format("no such algorithm, name = %s", algorithmName), e);
        }
        list.forEach(s -> digest.update(s.getBytes(StandardCharsets.UTF_8)));
        byte[] messageDigest = digest.digest();
        // Convert byte array into signum representation https://blog.csdn.net/qq_32451661/article/details/103429570
        BigInteger no = new BigInteger(1, messageDigest);
        StringBuilder hashtext = new StringBuilder(no.toString(16));
        while (hashtext.length() < 32) {
            hashtext.insert(0, "0");
        }
        return hashtext.toString();
    }
}
