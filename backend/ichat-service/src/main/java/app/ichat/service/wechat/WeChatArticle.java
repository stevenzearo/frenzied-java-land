package app.ichat.service.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Steve Zou
 */
public class WeChatArticle {
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
    @JsonProperty("need_open_comment")
    public Boolean needOpenComment;
    @JsonProperty("only_fans_can_comment")
    public Boolean onlyFansCanComment;
}
