package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Steve Zou
 */
public class ArticleView {
    public String title;
    public String author;
    public String digest;
    public String content;
    @JsonProperty("content_source_url")
    public String contentSourceUrl;
    @JsonProperty("thumb_media_id")
    public String thumbMediaId;
    @JsonProperty("show_cover_pic")
    public String showCoverPic;
    public String url;
    @JsonProperty("thumb_url")
    public String thumbUrl;
}
