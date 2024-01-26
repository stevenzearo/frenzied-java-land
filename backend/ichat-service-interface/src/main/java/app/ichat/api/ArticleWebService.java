package app.ichat.api;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.ichat.api.article.UpdateArticleRequest;
import app.web.response.EmptyResponse;
import app.web.response.Response;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "article-web-service", url = Context.ICHAT_SERVICE_URL)
public interface ArticleWebService {
    @GetMapping("article/{id}")
    Response<ArticleView> get(@PathVariable("id") String articleId);

    @GetMapping("/article")
    Response<List<ArticleView>> findAll();

    @GetMapping("/article/summary")
    Response<List<ArticleSummaryView>> findAllSummaries();

    @PutMapping("/article/summary")
    Response<SearchArticleSummaryResponse> findSummaries(@RequestBody SearchArticleSummaryRequest request);

    @PostMapping("/article")
    EmptyResponse createArticle(@RequestBody ArticleView article);

    @PutMapping("/article/{id}")
    EmptyResponse updateArticle(@PathVariable("id") String id, @RequestBody UpdateArticleRequest request);
}
