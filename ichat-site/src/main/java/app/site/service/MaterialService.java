package app.site.service;

import app.ichat.api.WeChatResourceWebService;
import app.ichat.api.material.MaterialContent;
import app.ichat.api.material.MaterialType;
import app.ichat.api.material.SearchMaterialRequest;
import app.ichat.api.material.SearchMaterialResponse;
import app.site.api.material.ArticleView;
import app.site.api.material.GetImageResponse;
import app.site.api.material.MaterialContentView;
import app.site.api.material.MaterialView;
import app.site.api.material.SearchMaterialAJAXRequest;
import app.site.api.material.SearchMaterialAJAXResponse;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class MaterialService {
    @Autowired
    WeChatResourceWebService resourceWebService;

    public GetImageResponse getGetImage(String url) {
        Response<byte[]> image = resourceWebService.getImage(url);
        byte[] data = ResponseHelper.fetchDataWithException(image);
        GetImageResponse response = new GetImageResponse();
        response.data = data;
        return response;
    }

    public SearchMaterialAJAXResponse searchMaterial(String accessToken, SearchMaterialAJAXRequest request) {
        Response<SearchMaterialResponse> response = resourceWebService.searchMaterial(accessToken, buildBORequest(request));
        SearchMaterialResponse boResponse = ResponseHelper.fetchDataWithException(response);
        return buildResponse(boResponse);
    }

    private static SearchMaterialAJAXResponse buildResponse(SearchMaterialResponse weChatResponse) {
        SearchMaterialAJAXResponse response = new SearchMaterialAJAXResponse();
        response.itemCount = weChatResponse.itemCount;
        response.totalCount = weChatResponse.totalCount;
        response.materials = weChatResponse.materials.stream().map(m -> {
            MaterialView material = new MaterialView();
            material.id = m.id;
            material.content = builldContent(m.content);
            material.updatedTime = m.updatedTime;
            return material;
        }).collect(Collectors.toList());
        return response;
    }

    private static MaterialContentView builldContent(MaterialContent weChatContent) {
        MaterialContentView content = new MaterialContentView();
        content.createdTime = weChatContent.createdTime;
        content.updatedTime = weChatContent.createdTime;
        content.articles = weChatContent.articles.stream().map(a -> {
            ArticleView article = new ArticleView();
            article.title = a.title;
            article.author = a.author;
            article.digest = a.digest;
            article.content = a.content;
            article.contentSourceUrl = a.contentSourceUrl;
            article.thumbMediaId = a.thumbMediaId;
            article.showCoverPic = a.showCoverPic;
            article.url = a.url;
            article.thumbUrl = a.thumbUrl;
            return article;
        }).collect(Collectors.toList());
        return content;
    }

    private static SearchMaterialRequest buildBORequest(SearchMaterialAJAXRequest request) {
        SearchMaterialRequest wechatRequest = new SearchMaterialRequest();
        wechatRequest.type = MaterialType.valueOf(request.type.name());
        wechatRequest.count = request.count;
        wechatRequest.offset = request.offset;
        return wechatRequest;
    }
}
