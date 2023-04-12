package app.ichat.service.turing;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.List;

/**
 * @author Steve Zou
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TuringChatResponse {
    public Integer code;
    public String msg;
    public ResponseData data;

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class ResponseData {

        public String answer;

        public Intent intent;
        public List<Item> items;

        public String parseModel;
        public Integer parseType;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Intent {
        public Integer code;
        public Integer operateState;

        public String intentName;

        public MusicInfo parameters;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class MusicInfo {
        public String singer;
        public String name;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        public ItemType type;
        public String value;
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
