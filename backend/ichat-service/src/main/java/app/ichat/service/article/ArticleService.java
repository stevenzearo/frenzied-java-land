package app.ichat.service.article;

import app.ichat.api.article.ArticleSummaryView;
import app.ichat.api.article.ArticleView;
import app.ichat.api.article.SearchArticleSummaryRequest;
import app.ichat.api.article.SearchArticleSummaryResponse;
import app.ichat.api.article.UpdateArticleRequest;
import app.ichat.domain.article.Article;
import app.ichat.domain.article.view.ArticleSummary;
import app.ichat.repository.ArticleRepository;
import app.ichat.repository.ArticleSummaryRepository;
import app.web.error.NotFoundException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ArticleService {
    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleSummaryRepository articleSummaryRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public ArticleView get(String articleId) {
        Optional<Article> articleOptional = articleRepository.findById(new ObjectId(articleId));
        if (articleOptional.isEmpty()) return null;
        Article article = articleOptional.get();
        return buildArticleView(article);
    }

    public List<ArticleView> findAll() {
        List<Article> collections = articleRepository.findAll();
        return collections.stream().map(ArticleService::buildArticleView).collect(Collectors.toList());
    }

    public List<ArticleSummaryView> findAllSummaries() {
        List<ArticleSummary> collections = articleSummaryRepository.findAll();
        return collections.stream().map(ArticleService::buildArticleSummaryView).collect(Collectors.toList());
    }

    public SearchArticleSummaryResponse findSummaries(SearchArticleSummaryRequest request) {
        Sort sort = buildSortOrders(request.sorts);
        PageRequest pageRequest = PageRequest.of(request.skip / request.limit, request.limit).withSort(sort);
        Page<ArticleSummary> summaryPage = articleSummaryRepository.findAll(pageRequest);
        long total = summaryPage.getTotalElements();
        List<ArticleSummaryView> summaryViews = summaryPage.get().map(ArticleService::buildArticleSummaryView).collect(Collectors.toList());
        SearchArticleSummaryResponse response = new SearchArticleSummaryResponse();
        response.total = (int) total;
        response.articleSummaryList = summaryViews;
        return response;
    }

    private static Sort buildSortOrders(List<SearchArticleSummaryRequest.Sort> sorts) {
        List<Sort.Order> orders = sorts.stream().map(sort -> {
            String sortBy = getSortBy(sort.sortBy);
            return new Sort.Order(sort.isDesc == Boolean.TRUE ? Sort.Direction.DESC : Sort.Direction.ASC, sortBy);
        }).collect(Collectors.toList());
        return Sort.by(orders);
    }

    private static String getSortBy(SearchArticleSummaryRequest.SortBy sortBy) {
        switch (sortBy) {
            case BY_CREATED_TIME:
            default:
                return "created_time";
        }
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
        article.createdTime = articleView.createdTime == null ? ZonedDateTime.now() : articleView.createdTime;
        articleRepository.save(article);
    }

    public void updateArticle(String id, UpdateArticleRequest request) {
        if (!articleRepository.existsById(new ObjectId(id))) {
            throw new NotFoundException("Article not found, id=" + id);
        }
        Article article = new Article();
        article.id = new ObjectId(id);
        article.title = request.title;
        article.author = request.author;
        article.digest = request.digest;
        article.content = request.content;
        article.contentSourceUrl = request.contentSourceUrl;
        article.thumbMediaId = request.thumbMediaId;
        article.showCoverPic = request.showCoverPic;
        article.url = request.url;
        article.thumbUrl = request.thumbUrl;
        article.createdTime = request.createdTime;
        articleRepository.save(article);
    }

    private static ArticleSummaryView buildArticleSummaryView(ArticleSummary c) {
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
        view.createdTime = c.createdTime;
        return view;
    }

    private static ArticleView buildArticleView(Article c) {
        ArticleView view = new ArticleView();
        view.id = c.id.toString();
        view.title = c.title;
        view.author = c.author;
        view.digest = c.digest;
        view.content = c.content;
        view.contentSourceUrl = c.contentSourceUrl;
        view.thumbMediaId = c.thumbMediaId;
        view.showCoverPic = c.showCoverPic;
        view.url = c.url;
        view.thumbUrl = c.thumbUrl;
        view.createdTime = c.createdTime;
        return view;
    }
}
