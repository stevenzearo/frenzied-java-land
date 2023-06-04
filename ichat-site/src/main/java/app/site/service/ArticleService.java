package app.site.service;

import app.ichat.api.ArticleWebService;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.site.api.article.SearchArticleSummaryAJAXRequest;
import app.site.api.article.SearchArticleSummaryAJAXResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Steve Zou
 */
@Service
public class ArticleService {
    @Autowired
    ArticleWebService articleWebService;
    public ArticleView get(@PathVariable("id") String articleId) {
        Response<ArticleView> response = articleWebService.get(articleId);
        return ResponseHelper.fetchDataWithException(response);
    }

    public SearchArticleSummaryAJAXResponse searchSummary(SearchArticleSummaryAJAXRequest request) {
        SearchArticleSummaryRequest articleSummaryRequest = buildSearchArticleSummaryRequest(request);
        Response<SearchArticleSummaryResponse> summaries = articleWebService.findSummaries(articleSummaryRequest);
        SearchArticleSummaryResponse boResponse = ResponseHelper.fetchDataWithException(summaries);
        SearchArticleSummaryAJAXResponse response = new SearchArticleSummaryAJAXResponse();
        response.articleSummaryList = boResponse.articleSummaryList;
        response.total = boResponse.total;
        return response;
    }

    private static SearchArticleSummaryRequest buildSearchArticleSummaryRequest(SearchArticleSummaryAJAXRequest request) {
        SearchArticleSummaryRequest articleSummaryRequest = new SearchArticleSummaryRequest();
        articleSummaryRequest.skip = request.skip;
        articleSummaryRequest.limit = request.limit;
        articleSummaryRequest.sorts = request.sorts == null ? List.of() : request.sorts.stream().map(sort -> {
            SearchArticleSummaryRequest.Sort sortReq = new SearchArticleSummaryRequest.Sort();
            sortReq.sortBy = SearchArticleSummaryRequest.SortBy.valueOf(sort.sortBy.name());
            sortReq.isDesc = sort.isDesc == null ? Boolean.FALSE : sort.isDesc;
            return sortReq;
        }).collect(Collectors.toList());
        return articleSummaryRequest;
    }
}
