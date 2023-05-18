package app.ichat.api;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "article-web-service")
public interface ArticleWebService {

    @GetMapping("/article")
    List<ArticleView> findAll();

    @GetMapping("/article/summary")
    List<ArticleSummaryView> findAllSummaries();

    @PutMapping("/article/summary")
    SearchArticleSummaryResponse findSummaries(@RequestBody SearchArticleSummaryRequest request);

    @PostMapping("/article")
    void createArticle(@RequestBody ArticleView article);
}
