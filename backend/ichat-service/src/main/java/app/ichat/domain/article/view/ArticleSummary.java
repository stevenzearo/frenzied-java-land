package app.ichat.domain.article.view;

import app.ichat.domain.article.Article;
import java.time.ZonedDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Steve Zou
 */

@Document("articles")
public class ArticleSummary {
    @Id
    public ObjectId id;
    @Field("title")
    public String title;
    @Field("author")
    public String author;
    @Field("digest")
    public String digest;
    @Field("content_source_url")
    public String contentSourceUrl;
    @Field("thumb_media_id")
    public String thumbMediaId;
    @Field("show_cover_pic")
    public String showCoverPic;
    @Field("url")
    public String url;
    @Field("thumb_url")
    public String thumbUrl;
    @Field("created_time")
    public ZonedDateTime createdTime;
}
