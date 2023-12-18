package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * @author Steve Zou
 */
public enum MaterialType {
    NEWS("news");

    @JsonValue
    public final String value;

    MaterialType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
