package app.ichat.repository;

import app.ichat.domain.article.view.ArticleSummary;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleSummaryRepository extends MongoRepository<ArticleSummary, ObjectId> {
}
