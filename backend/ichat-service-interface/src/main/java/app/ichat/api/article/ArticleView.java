package app.ichat.api.article;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Steve Zou
 */

public class ArticleView {
    public String id;
    @JsonAlias("title")
    public String title;
    @JsonAlias("author")
    public String author;
    @JsonAlias("digest")
    public String digest;
    @JsonAlias("content")
    public String content;
    @JsonAlias("content_source_url")
    public String contentSourceUrl;
    @JsonAlias("thumb_media_id")
    public String thumbMediaId;
    @JsonAlias("show_cover_pic")
    public String showCoverPic;
    @JsonAlias("url")
    public String url;
    @JsonAlias("thumb_url")
    public String thumbUrl;
}
