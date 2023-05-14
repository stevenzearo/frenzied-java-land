package app.ichat.api;

import app.ichat.api.article.MyCollectionView;
import app.ichat.service.article.ArticleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
public class ArticleWebServiceImpl implements ArticleWebService {
    @Autowired
    ArticleService articleService;

    @Override
    public List<MyCollectionView> findAll() {
        return articleService.findAll();
    }
}
