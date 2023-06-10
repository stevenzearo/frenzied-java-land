package app.ichat.api.article;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;

/**
 * @author Steve Zou
 */
public class SearchArticleSummaryRequest {
    public Integer skip;
    public Integer limit;
    @NotNull
    public List<Sort> sorts = new ArrayList<>();
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
