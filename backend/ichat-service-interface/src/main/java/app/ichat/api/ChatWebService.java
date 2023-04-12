package app.ichat.api;

import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.web.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
@FeignClient(value = "ichat-service", qualifiers = "chat-web-service")
public interface ChatWebService {
    @RequestMapping(value = "/chat/{id}", method = RequestMethod.POST)
    Response<ChatResponse> chat(@PathVariable("id") String id, @RequestBody ChatRequest request);
}
