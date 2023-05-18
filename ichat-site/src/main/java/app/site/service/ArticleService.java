package app.site.service;

import app.ichat.api.ArticleWebService;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.site.api.article.SearchArticleSummaryAJAXRequest;
import app.site.api.article.SearchArticleSummaryAJAXResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ArticleService {
    @Autowired
    ArticleWebService articleWebService;

    public SearchArticleSummaryAJAXResponse searchSummary(SearchArticleSummaryAJAXRequest request) {
        SearchArticleSummaryRequest articleSummaryRequest = new SearchArticleSummaryRequest();
        articleSummaryRequest.skip = request.skip;
        articleSummaryRequest.limit = request.limit;
        SearchArticleSummaryResponse boResponse = articleWebService.findSummaries(articleSummaryRequest);
        SearchArticleSummaryAJAXResponse response = new SearchArticleSummaryAJAXResponse();
        response.articleSummaryList = boResponse.articleSummaryList;
        response.total = boResponse.total;
        return response;
    }
}
