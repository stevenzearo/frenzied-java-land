package app.ichat.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Steve Zou
 */
public class UploadMaterialResponse {
    @JsonProperty(namespace = "media_id")
    public String mediaId;

    @JsonProperty(namespace = "url")
    public String url;
}
