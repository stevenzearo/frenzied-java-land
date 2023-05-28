package app.ichat.api.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;

/**
 * @author Steve Zou
 */

public class ArticleView {
    @JsonProperty("id")
    public String id;
    @JsonProperty("title")
    public String title;
    @JsonProperty("author")
    public String author;
    @JsonProperty("digest")
    public String digest;
    @JsonProperty("content")
    public String content;
    @JsonProperty("content_source_url")
    public String contentSourceUrl;
    @JsonProperty("thumb_media_id")
    public String thumbMediaId;
    @JsonProperty("show_cover_pic")
    public String showCoverPic;
    @JsonProperty("url")
    public String url;
    @JsonProperty("thumb_url")
    public String thumbUrl;
    @JsonProperty("created_time")
    public ZonedDateTime createdTime;
}
