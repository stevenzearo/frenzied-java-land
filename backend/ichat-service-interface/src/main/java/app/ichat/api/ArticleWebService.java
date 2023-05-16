package app.ichat.api;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "article-web-service")
public interface ArticleWebService {

    @GetMapping("/article")
    public List<ArticleView> findAll();

    @GetMapping("/article/summary")
    public List<ArticleSummaryView> findAllSummaries();

    @PostMapping("/article")
    public void createArticle(@RequestBody ArticleView article);
}
