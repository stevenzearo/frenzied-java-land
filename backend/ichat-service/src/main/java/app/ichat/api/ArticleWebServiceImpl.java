package app.ichat.api;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.ichat.api.article.UpdateArticleRequest;
import app.ichat.service.article.ArticleService;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
public class ArticleWebServiceImpl implements ArticleWebService {
    @Autowired
    ArticleService articleService;

    @Override
    public Response<ArticleView> get(String articleId) {
        return ResponseHelper.okOf(articleService.get(articleId));
    }

    @Override
    public Response<List<ArticleView>> findAll() {
        return ResponseHelper.okOf(articleService.findAll());
    }

    @Override
    public Response<List<ArticleSummaryView>> findAllSummaries() {
        return ResponseHelper.okOf(articleService.findAllSummaries());
    }

    @Override
    public Response<SearchArticleSummaryResponse> findSummaries(SearchArticleSummaryRequest request) {
        return ResponseHelper.okOf(articleService.findSummaries(request));
    }

    @Override
    public EmptyResponse createArticle(ArticleView article) {
        articleService.createArticle(article);
        return ResponseHelper.ok();
    }

    @Override
    public EmptyResponse updateArticle(String id, UpdateArticleRequest request) {
        return ResponseHelper.encloseWithException(() -> articleService.updateArticle(id, request));
    }
}
