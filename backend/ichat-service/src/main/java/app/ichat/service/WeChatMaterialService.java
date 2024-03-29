package app.ichat.service;

import app.ichat.api.material.Article;
import app.ichat.api.material.Material;
import app.ichat.api.material.MaterialContent;
import app.ichat.api.material.SearchMaterialRequest;
import app.ichat.api.material.SearchMaterialResponse;
import app.ichat.api.material.UploadMaterialRequest;
import app.ichat.api.material.UploadMaterialResponse;
import app.ichat.service.util.UrlDecodeUtil;
import app.ichat.service.wechat.WeChatError;
import app.ichat.service.wechat.WeChatMaterialContent;
import app.ichat.service.wechat.WeChatSearchMaterialRequest;
import app.ichat.service.wechat.WeChatSearchMaterialResponse;
import app.ichat.service.wechat.WechatMaterialType;
import app.web.error.NotFoundException;
import app.web.error.WeChatIntegrationException;
import app.web.error.WebErrorCodes;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jetbrains.annotations.NotNull;
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
    private static final String UPLOAD_MATERIAL_URL = "https://api.weixin.qq.com/cgi-bin/material/add_material";
    private final Logger logger = LoggerFactory.getLogger(WeChatMaterialService.class);
    @Autowired(required = false)
    ObjectMapper mapper;

    public SearchMaterialResponse searchMaterial(String accessToken, SearchMaterialRequest request) {
        WeChatSearchMaterialRequest weChatRequest = buildWeChatRequest(request);
        WeChatSearchMaterialResponse weChatResponse = searchWeChatMaterialResponse(accessToken, weChatRequest);
        return buildResponse(weChatResponse);
    }

    public UploadMaterialResponse uploadMaterial(String accessToken, UploadMaterialRequest request) {
        File file = new File(request.filePath);
        if (!file.exists()) {
            throw new NotFoundException(String.format("File not found path=%s", request.filePath));
        }

        MultipartBody multipartBody = buildMultipartBody(file);
        HttpUrl httpUrl = buildHttpUrl(accessToken, request);
        Request httpRequest = new Request.Builder().url(httpUrl)
            .post(multipartBody).build();
        String responseStr = "";

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Call call = okHttpClient.newCall(httpRequest);
        try (Response response = call.execute()) {
            ResponseBody body = response.body();
            if (body == null) {
                throw new WeChatIntegrationException("Can not get response body from wechat API, url=" + httpUrl.url());
            }
            responseStr = body.string();
            logger.info("Wechat material response: {}", responseStr);
            if (responseStr.startsWith("{\"errcode\"")) {
                WeChatError weChatError = mapper.readValue(responseStr, WeChatError.class);
                if (WeChatError.ErrorCode.TOKEN_EXPIRED.value.equals(weChatError.errorCode)) {
                    throw new WeChatIntegrationException(WebErrorCodes.WECHAT_TOKEN_EXPIRED, "Upload wechat material failed, errorMsg=" + responseStr);
                } else if (WeChatError.ErrorCode.TOKEN_INVALID.value.equals(weChatError.errorCode)) {
                    throw new WeChatIntegrationException(WebErrorCodes.WECHAT_TOKEN_INVALID, "Upload wechat material failed, errorMsg=" + responseStr);
                } else {
                    throw new WeChatIntegrationException("Upload wechat material failed, errorMsg=" + responseStr);
                }
            } else {
                return mapper.readValue(responseStr, UploadMaterialResponse.class);
            }
        } catch (IOException e) {
            throw new WeChatIntegrationException(String.format("Failed to get image, url=%s, responseStr=%s", UPLOAD_MATERIAL_URL, responseStr), e);
        }
    }

    @NotNull
    private static MultipartBody buildMultipartBody(File file) {
        MediaType mediaType = MediaType.parse(ContentType.MULTIPART_FORM_DATA.getMimeType());
        RequestBody requestBody = MultipartBody.create(file, mediaType);
        MultipartBody.Part part = MultipartBody.Part.createFormData("media", file.getName(), requestBody);
        return new MultipartBody.Builder().setType(Objects.requireNonNull(mediaType)).addPart(part).build();
    }

    @NotNull
    private static HttpUrl buildHttpUrl(String accessToken, UploadMaterialRequest request) {
        HttpUrl.Builder builder = UrlDecodeUtil.toHttpUrlBuilder(UPLOAD_MATERIAL_URL);
        builder.addQueryParameter("access_token", accessToken);
        builder.addQueryParameter("type", request.type.value);
        return builder.build();
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
            if (responseStr.startsWith("{\"errcode\"")) {
                WeChatError weChatError = mapper.readValue(responseStr, WeChatError.class);
                if (WeChatError.ErrorCode.TOKEN_EXPIRED.value.equals(weChatError.errorCode)) {
                    throw new WeChatIntegrationException(WebErrorCodes.WECHAT_TOKEN_EXPIRED, "Search wechat material failed, errorMsg=" + responseStr);
                } else if (WeChatError.ErrorCode.TOKEN_INVALID.value.equals(weChatError.errorCode)) {
                    throw new WeChatIntegrationException(WebErrorCodes.WECHAT_TOKEN_INVALID, "Search wechat material failed, errorMsg=" + responseStr);
                } else {
                    throw new WeChatIntegrationException("Search wechat material failed, errorMsg=" + responseStr);
                }
            } else {
                return mapper.readValue(responseStr, WeChatSearchMaterialResponse.class);
            }
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
