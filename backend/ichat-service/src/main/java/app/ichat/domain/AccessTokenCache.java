package app.ichat.domain;

import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class AccessTokenCache {
    public ZonedDateTime expiredTime;
    public String accessToken;
}
