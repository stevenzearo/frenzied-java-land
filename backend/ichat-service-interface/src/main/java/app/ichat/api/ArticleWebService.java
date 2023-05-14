package app.ichat.api;

import app.ichat.api.article.MyCollectionView;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "article-web-service")
public interface ArticleWebService {

    @GetMapping("/my_collection")
    public List<MyCollectionView> findAll();
}
