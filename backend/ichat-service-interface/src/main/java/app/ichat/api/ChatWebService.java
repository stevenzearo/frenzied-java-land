package app.ichat.api;

import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Steve Zou
 */
@FeignClient(value = "ichat-service", qualifiers = "chat-web-service")
public interface ChatWebService {
    @PostMapping(value = "/chat/{id}")
    Response<ChatResponse> chat(@PathVariable("id") String id, @RequestBody ChatRequest request);
}
