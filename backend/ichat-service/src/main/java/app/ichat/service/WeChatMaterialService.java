package app.ichat.service;

import app.ichat.api.material.Article;
import app.ichat.api.material.Material;
import app.ichat.api.material.MaterialContent;
import app.ichat.api.material.SearchMaterialRequest;
import app.ichat.api.material.SearchMaterialResponse;
import app.ichat.service.wechat.WeChatMaterialContent;
import app.ichat.service.wechat.WeChatSearchMaterialRequest;
import app.ichat.service.wechat.WeChatSearchMaterialResponse;
import app.ichat.service.wechat.WechatMaterialType;
import app.web.error.WeChatIntegrationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class WeChatMaterialService {
    private static final String MATERIAL_RESOURCE_URL = "https://api.weixin.qq.com/cgi-bin/material/batchget_material";
    private final Logger logger = LoggerFactory.getLogger(WeChatMaterialService.class);
    @Autowired
    ObjectMapper mapper;

    public SearchMaterialResponse searchMaterial(String accessToken, SearchMaterialRequest request) {
        WeChatSearchMaterialRequest weChatRequest = buildWeChatRequest(request);
        WeChatSearchMaterialResponse weChatResponse = searchWeChatMaterialResponse(accessToken, weChatRequest);
        return buildResponse(weChatResponse);
    }

    private WeChatSearchMaterialResponse searchWeChatMaterialResponse(String accessToken, WeChatSearchMaterialRequest request) {
        String url = MATERIAL_RESOURCE_URL;
        String responseStr = "";
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String string = mapper.writeValueAsString(request);
            StringEntity stringEntity = new StringEntity(string, ContentType.APPLICATION_JSON);

            HttpUriRequest httpRequest = RequestBuilder.post()
                .setUri(url)
                .addParameter("access_token", accessToken)
                .setEntity(stringEntity)
                .build();
            CloseableHttpResponse httpResponse = httpClient.execute(httpRequest);
            HttpEntity entity = httpResponse.getEntity();
            responseStr = new String(entity.getContent().readAllBytes());
            logger.info("Wechat material response: {}", responseStr);
            return mapper.readValue(responseStr, WeChatSearchMaterialResponse.class);
        } catch (IOException e) {
            throw new WeChatIntegrationException(String.format("Failed to get image, url=%s, responseStr=%s", url, responseStr), e);
        }
    }

    private static SearchMaterialResponse buildResponse(WeChatSearchMaterialResponse weChatResponse) {
        SearchMaterialResponse response = new SearchMaterialResponse();
        response.itemCount = weChatResponse.itemCount;
        response.totalCount = weChatResponse.totalCount;
        response.materials = weChatResponse.materials.stream().map(m -> {
            Material material = new Material();
            material.id = m.id;
            material.content = builldContent(m.content);
            material.updatedTime = m.updatedTime;
            return material;
        }).collect(Collectors.toList());
        return response;
    }

    private static MaterialContent builldContent(WeChatMaterialContent weChatContent) {
        MaterialContent content = new MaterialContent();
        content.createdTime = weChatContent.createdTime;
        content.updatedTime = weChatContent.createdTime;
        content.articles = weChatContent.articles.stream().map(a -> {
            Article article = new Article();
            article.title = a.title;
            article.author = a.author;
            article.digest = a.digest;
            article.content = a.content;
            article.contentSourceUrl = a.contentSourceUrl;
            article.thumbMediaId = a.thumbMediaId;
            article.showCoverPic = a.showCoverPic;
            article.url = a.url;
            article.thumbUrl = a.thumbUrl;
            article.needOpenComment = a.needOpenComment;
            article.onlyFansCanComment = a.onlyFansCanComment;
            return article;
        }).collect(Collectors.toList());
        return content;
    }

    private static WeChatSearchMaterialRequest buildWeChatRequest(SearchMaterialRequest request) {
        WeChatSearchMaterialRequest wechatRequest = new WeChatSearchMaterialRequest();
        wechatRequest.type = WechatMaterialType.valueOf(request.type.value);
        wechatRequest.count = request.count;
        wechatRequest.offset = request.offset;
        return wechatRequest;
    }
}
