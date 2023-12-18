package app.ichat.service;

import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.ichat.service.turing.TuringChatRequest;
import app.ichat.service.turing.TuringChatResponse;
import app.web.error.TuringIntegrationException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ChatService {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);
    private static final String TRUING_CHAT_URL = "http://chatbot-api.turingapi.com/v1/wechat";
    @Autowired(required = false)
    private ObjectMapper mapper;

    public ChatResponse chat(String id, ChatRequest request) {
        TuringChatRequest turingChatRequest = new TuringChatRequest();
        turingChatRequest.openId = id;
        turingChatRequest.text = request.text;
        TuringChatResponse turingChatResponse = chatToTuring(turingChatRequest);
        if (HttpStatus.OK.value() != turingChatResponse.code || turingChatResponse.data == null) {
            throw new TuringIntegrationException("Failed to chat with Turing platform, code: " + turingChatResponse.code + ", msg: " + turingChatResponse.msg);
        }
        return buildChatResponse(turingChatResponse.data);
    }

    public TuringChatResponse chatToTuring(TuringChatRequest turingChatRequest) {
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            String reqEntity = mapper.writeValueAsString(turingChatRequest);
            logger.info("chat request entity: {}", reqEntity);
            StringEntity stringEntity = new StringEntity(reqEntity, ContentType.APPLICATION_JSON);
            HttpUriRequest request = RequestBuilder.post()
                .setUri(TRUING_CHAT_URL)
                .setEntity(stringEntity)
                .build();
            CloseableHttpResponse httpResponse = httpClient.execute(request);
            HttpEntity entity = httpResponse.getEntity();
            String s = EntityUtils.toString(entity);
            logger.info("chat response entity: {}", s);
            return mapper.readValue(s, TuringChatResponse.class);
        } catch (IOException e) {
            throw new TuringIntegrationException("Failed to chat with Turing platform.", e);
        }
    }

    private static ChatResponse buildChatResponse(TuringChatResponse.ResponseData turingResponseData) {
        ChatResponse response = new ChatResponse();
        response.answer = turingResponseData.answer;
        response.items = Optional.ofNullable(turingResponseData.items).orElse(new ArrayList<>())
            .stream().map(item -> {
                ChatResponse.Item itemView = new ChatResponse.Item();
                itemView.type = ChatResponse.ItemType.valueOf(item.type.name());
                itemView.value = item.value;
                if (TuringChatResponse.ItemType.VOICE == item.type
                    && turingResponseData.intent != null
                    && turingResponseData.intent.parameters != null) {
                    itemView.musicName = turingResponseData.intent.parameters.name;
                    itemView.singer = turingResponseData.intent.parameters.singer;
                }
                return itemView;
            }).collect(Collectors.toList());
        return response;
    }
}
