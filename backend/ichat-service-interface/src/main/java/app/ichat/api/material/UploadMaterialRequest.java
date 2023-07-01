package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Steve Zou
 */
public class UploadMaterialRequest {
    @JsonProperty("file_path")
    public String filePath;

    @JsonProperty("type")
    public Type type;

    public static enum Type {
        IMAGE("image"),
        VOICE("voice"),
        VIDEO("video"),
        THUMB("thumb");

        @JsonValue
        public final String value;

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }
    }
}
