package app.ichat.api.material;

/**
 * @author Steve Zou
 */
public enum MaterialType {
    NEWS("news");

    public final String value;

    MaterialType(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
