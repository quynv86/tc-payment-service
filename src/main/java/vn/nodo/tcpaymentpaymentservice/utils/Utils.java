package vn.nodo.tcpaymentpaymentservice.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Utils {

    private static final Logger logger = LoggerFactory.getLogger(Utils.class);

    public static String generateSignature(String... params) {
        StringBuilder sig = new StringBuilder();
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            byte[] hash = digest.digest(String.join("|", params).getBytes(StandardCharsets.UTF_8));
            for (byte b : hash) {
                String s = String.format("%02x", b);
                sig.append(s);
            }
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage(), e);
        }
        return sig.toString();
    }
}
