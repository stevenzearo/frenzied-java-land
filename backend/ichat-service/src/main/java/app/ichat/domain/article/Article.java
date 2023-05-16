package app.ichat.domain.article;

import com.fasterxml.jackson.annotation.JsonAlias;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Steve Zou
 */
@Document("articles")
public class Article {
    @Id
    public ObjectId id;
    @Field("title")
    public String title;
    @Field("author")
    public String author;
    @Field("digest")
    public String digest;
    @Field("content")
    public String content;
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
}