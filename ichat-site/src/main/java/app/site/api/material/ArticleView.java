package app.site.api.material;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * @author Steve Zou
 */
public class ArticleView {
    public String title;
    public String author;
    public String digest;
    public String content;
    @JsonAlias("content_source_url")
    public String contentSourceUrl;
    @JsonAlias("thumb_media_id")
    public String thumbMediaId;
    @JsonAlias("show_cover_pic")
    public String showCoverPic;
    public String url;
    @JsonAlias("thumb_url")
    public String thumbUrl;
    /*
    @JsonAlias("need_open_comment")
    public Boolean needOpenComment;
    @JsonAlias("only_fans_can_comment")
    public Boolean onlyFansCanComment;
    */
}
