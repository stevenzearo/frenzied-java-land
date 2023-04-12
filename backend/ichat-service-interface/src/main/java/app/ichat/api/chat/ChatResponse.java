package app.ichat.api.chat;

import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;

/**
 * @author Steve Zou
 */
public class ChatResponse {
    public String answer;
    public List<Item> items;

    public static class Item {
        public ItemType type;
        public String value; // when type is voice, value should be url of this voice
        public String singer;
        public String musicName;
    }

    public enum ItemType {
        TEXT("text"),
        VOICE("voice");

        @JsonValue
        public final String value;

        ItemType(String value) {
            this.value = value;
        }
    }
}
