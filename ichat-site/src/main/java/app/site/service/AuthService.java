package app.site.service;

import app.util.EncryptException;
import app.util.EncryptHelper;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class AuthService {
    private static final String TOKEN = "aabc";
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);

    public String auth(String signature, String timestamp, String nonce, String echoStr) {
        ArrayList<String> list = new ArrayList<>();
        list.add(TOKEN);
        list.add(timestamp);
        list.add(nonce);
        list.sort(String::compareTo);
        String shaStr;
        try {
            shaStr = EncryptHelper.encryptSha1(list);
        } catch (EncryptException e) {
            logger.warn("encrypt input failed", e);
            return "";
        }
        if (shaStr.equals(signature)) {
            return echoStr;
        } else {
            logger.warn("invalid signature");
            return "";
        }
    }

    public String getAccessToken() {
        // todo
        return "";
    }
}
