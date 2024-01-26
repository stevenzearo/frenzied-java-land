package api.client;

import app.site.model.ChatCache;
import app.site.service.ChatCacheService;
import java.time.Duration;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author Steve Zou
 */
public class CacheTest {
    @Test
    public void testCache() throws InterruptedException {
        ChatCacheService chatCacheService = new ChatCacheService();
        ChatCacheService.EXPIRE_DURATION = Duration.ofSeconds(3);
        chatCacheService.connect("user-001");
        chatCacheService.connect("user-002");
        Thread.sleep(4000);
        ChatCache chatCache1 = chatCacheService.connect("user-001");
        ConcurrentHashMap<String, ChatCache> map = chatCacheService.getChatCacheMap();
        ChatCache chatCache2 = map.get("user-001");
        Assertions.assertNotNull(chatCache2);
        Assertions.assertEquals(1, map.size());
        Assertions.assertEquals(chatCache1.salt, chatCache2.salt);
    }
}
