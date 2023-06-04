package app.site.api.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * @author Steve Zou
 */
public class SearchArticleSummaryAJAXRequest {
    public Integer skip;
    public Integer limit;
    public List<Sort> sorts;

    public enum SortBy {
        BY_CREATED_TIME
    }

    public static class Sort {
        @JsonProperty("sort_by")
        public SortBy sortBy;
        @JsonProperty("is_desc")
        public Boolean isDesc = Boolean.FALSE;
    }
}
