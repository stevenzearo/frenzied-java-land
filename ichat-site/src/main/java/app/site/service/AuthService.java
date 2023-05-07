package app.site.service;

import app.ichat.api.AuthWebService;
import app.util.EncryptException;
import app.util.EncryptHelper;
import app.web.response.ResponseHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class AuthService {
    private static final String TOKEN = "aabc";
    private final Logger logger = LoggerFactory.getLogger(AuthService.class);
    @Autowired
    AuthWebService authWebService;

    public String auth(String signature, String timestamp, String nonce, String echoStr) {
        if (signature == null || signature.trim().isEmpty()) {
            logger.warn("empty signature");
            return "";
        }
        List<String> list = new ArrayList<>();
        list.add(TOKEN);
        list.add(timestamp);
        list.add(nonce);
        list = list.stream().filter(Objects::nonNull).sorted(String::compareTo).collect(Collectors.toList());
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
        return ResponseHelper.fetchDataWithException(authWebService.getAccessToken());
    }
}
