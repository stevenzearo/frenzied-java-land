package app.ichat.service.article;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import app.ichat.domain.article.Article;
import app.ichat.domain.article.view.ArticleSummary;
import app.ichat.repository.ArticleRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;

    public List<ArticleView> findAll() {
        List<Article> collections = articleRepository.findAll();
        return collections.stream().map(c -> {
            ArticleView view = new ArticleView();
            view.id = c.id.toString();
            view.title = c.title;
            view.author = c.author;
            view.digest = c.digest;
            view.contentSourceUrl = c.contentSourceUrl;
            view.thumbMediaId = c.thumbMediaId;
            view.showCoverPic = c.showCoverPic;
            view.url = c.url;
            view.thumbUrl = c.thumbUrl;
            return view;
        }).collect(Collectors.toList());
    }

    public List<ArticleSummaryView> findAllSummaries() {
        // todo find another projection way
        List<ArticleSummary> collections = articleRepository.findAllSummaries();
        return collections.stream().map(c -> {
            ArticleSummaryView view = new ArticleSummaryView();
            view.id = c.id.toString();
            view.title = c.title;
            view.author = c.author;
            view.digest = c.digest;
            view.contentSourceUrl = c.contentSourceUrl;
            view.thumbMediaId = c.thumbMediaId;
            view.showCoverPic = c.showCoverPic;
            view.url = c.url;
            view.thumbUrl = c.thumbUrl;
            return view;
        }).collect(Collectors.toList());
    }

    public void createArticle(ArticleView articleView) {
        Article article = new Article();
        article.title = articleView.title;
        article.author = articleView.author;
        article.digest = articleView.digest;
        article.content = articleView.content;
        article.contentSourceUrl = articleView.contentSourceUrl;
        article.thumbMediaId = articleView.thumbMediaId;
        article.showCoverPic = articleView.showCoverPic;
        article.url = articleView.url;
        article.thumbUrl = articleView.thumbUrl;
        articleRepository.save(article);
    }
}
