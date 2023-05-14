package app.ichat.repository;

import app.ichat.domain.article.MyCollection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MyCollectionRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<MyCollection> findAll() {
        return mongoTemplate.findAll(MyCollection.class);
    }

    public void save(MyCollection myCollection) {
        mongoTemplate.save(myCollection);
    }
}
