package app.site.model;

import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */
public class ChatCache {
    public String userId;
    public String salt;
    public ZonedDateTime connectionTime;
}
