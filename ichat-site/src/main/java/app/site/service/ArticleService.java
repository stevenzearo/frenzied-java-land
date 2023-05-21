package app.site.service;

import app.ichat.api.ArticleWebService;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.site.api.article.SearchArticleSummaryAJAXRequest;
import app.site.api.article.SearchArticleSummaryAJAXResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
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
        SearchArticleSummaryRequest articleSummaryRequest = new SearchArticleSummaryRequest();
        articleSummaryRequest.skip = request.skip;
        articleSummaryRequest.limit = request.limit;
        Response<SearchArticleSummaryResponse> summaries = articleWebService.findSummaries(articleSummaryRequest);
        SearchArticleSummaryResponse boResponse = ResponseHelper.fetchDataWithException(summaries);
        SearchArticleSummaryAJAXResponse response = new SearchArticleSummaryAJAXResponse();
        response.articleSummaryList = boResponse.articleSummaryList;
        response.total = boResponse.total;
        return response;
    }
}
