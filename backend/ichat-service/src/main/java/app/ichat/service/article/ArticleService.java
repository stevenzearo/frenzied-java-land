package app.ichat.service.article;

import app.ichat.api.article.MyCollectionView;
import app.ichat.domain.article.MyCollection;
import app.ichat.repository.MyCollectionRepository;
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
    MyCollectionRepository myCollectionRepository;

    public List<MyCollectionView> findAll() {
        List<MyCollection> collections = myCollectionRepository.findAll();
        return collections.stream().map(c -> {
            MyCollectionView myCollectionView = new MyCollectionView();
            myCollectionView.name = c.name;
            myCollectionView.age = c.age;
            return myCollectionView;
        }).collect(Collectors.toList());
    }
}
