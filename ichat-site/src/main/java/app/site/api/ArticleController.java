package app.site.api;

import app.ichat.api.article.ArticleView;
import app.site.api.article.SearchArticleSummaryAJAXRequest;
import app.site.api.article.SearchArticleSummaryAJAXResponse;
import app.site.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@CrossOrigin
@RestController
public class ArticleController {
    private final Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

    @ResponseBody
    @GetMapping("article/{id}")
    public ArticleView get(@PathVariable("id") String articleId) {
        return articleService.get(articleId);
    }

    @ResponseBody
    @PutMapping("/article/summary")
    public SearchArticleSummaryAJAXResponse searchSummary(@RequestBody SearchArticleSummaryAJAXRequest request) {
        return articleService.searchSummary(request);
    }
}
