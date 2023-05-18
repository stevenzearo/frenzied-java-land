package app.ichat.api;

import app.ichat.api.chat.ChatRequest;
import app.ichat.api.chat.ChatResponse;
import app.ichat.service.ChatService;
import app.web.response.Response;
import app.web.response.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Steve Zou
 */
@RestController
public class ChatWebServiceImpl implements ChatWebService {
    @Autowired
    ChatService chatService;

    @Override
    public Response<ChatResponse> chat(String id, ChatRequest request) {
        return ResponseHelper.okOf(chatService.chat(id, request));
    }
}
