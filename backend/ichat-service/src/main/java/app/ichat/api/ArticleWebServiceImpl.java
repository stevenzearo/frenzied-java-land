package app.ichat.api;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.ichat.service.article.ArticleService;
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
    public List<ArticleView> findAll() {
        return articleService.findAll();
    }

    @Override
    public List<ArticleSummaryView> findAllSummaries() {
        return articleService.findAllSummaries();
    }

    @Override
    public SearchArticleSummaryResponse findSummaries(SearchArticleSummaryRequest request) {
        return articleService.findSummaries(request);
    }

    @Override
    public void createArticle(ArticleView article) {
        articleService.createArticle(article);
    }
}
