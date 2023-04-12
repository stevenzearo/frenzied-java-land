package app.site.service;

import app.site.model.ChatCache;
import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Component;

/**
 * @author Steve Zou
 */
@Component
public class ChatCacheService {
    private static final Object lock = new Object();
    private volatile ConcurrentHashMap<String, ChatCache> chatCacheMap;
    public static Duration EXPIRE_DURATION = Duration.ofMinutes(30);

    public ChatCache connect(String userId) {
        if (chatCacheMap == null) {
            synchronized (lock) {
                if (chatCacheMap == null) {
                    chatCacheMap = new ConcurrentHashMap<>();
                }
            }
        }
        ChatCache chatCache = setNewCache(userId);
        clearExpired();
        return chatCache;
    }

    private ChatCache setNewCache(String userId) {
        ChatCache newChatCache = new ChatCache();
        newChatCache.userId = userId;
        newChatCache.salt = UUID.randomUUID().toString();
        newChatCache.connectionTime = ZonedDateTime.now().plus(EXPIRE_DURATION);
        ChatCache chatCache = chatCacheMap.get(userId);
        if (chatCache == null) {
            chatCacheMap.put(userId, newChatCache);
            return newChatCache;
        } else {
            chatCache.connectionTime = ZonedDateTime.now().plus(EXPIRE_DURATION);
            chatCacheMap.put(userId, chatCache);
            return chatCache;
        }
    }

    public void clearExpired() {
        chatCacheMap.values().removeIf(e -> ZonedDateTime.now().isAfter(e.connectionTime));
    }

    public ConcurrentHashMap<String, ChatCache> getChatCacheMap() {
        return this.chatCacheMap;
    }
}
