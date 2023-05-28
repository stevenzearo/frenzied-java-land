package app.ichat.domain.article;

import app.ichat.domain.article.view.ArticleSummary;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author Steve Zou
 */
public class Article extends ArticleSummary {
    @Field("content")
    public String content;
}