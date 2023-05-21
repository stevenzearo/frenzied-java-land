package app.ichat.repository;

import app.ichat.domain.article.Article;
import app.ichat.domain.article.view.ArticleSummary;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Article get(String id) {
        ObjectId objectId = new ObjectId(id);
        Criteria idClause = Criteria.where("_id").is(objectId);
        Query query = new Query();
        query.addCriteria(idClause);
        return mongoTemplate.findOne(query, Article.class);
    }

    public List<Article> findAll() {
        Query query = new Query();
        return mongoTemplate.find(query, Article.class);
    }

    public List<ArticleSummary> findAllSummaries() {
        Query query = new Query();
        return mongoTemplate.find(query, ArticleSummary.class);
    }

    public List<ArticleSummary> findSummaries(Integer skip, Integer limit) {
        Query query = new Query();
        if (skip != null) {
            query.skip(skip);
        }
        if (limit != null) {
            query.limit(limit);
        }
        return mongoTemplate.find(query, ArticleSummary.class);
    }

    public void save(Article article) {
        mongoTemplate.save(article);
    }

    public Integer count() {
        return (int) mongoTemplate.count(new Query(), Article.class);
    }
}
