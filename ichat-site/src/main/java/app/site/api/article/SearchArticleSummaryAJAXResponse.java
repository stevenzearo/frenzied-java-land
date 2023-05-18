package app.site.api.article;

import app.ichat.api.article.ArticleSummaryView;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @author Steve Zou
 */
public class SearchArticleSummaryAJAXResponse {
    @JsonProperty("total")
    public Integer total;
    @JsonProperty("article_summaries")
    public List<ArticleSummaryView> articleSummaryList;
}
