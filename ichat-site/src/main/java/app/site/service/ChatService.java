package app.site.service;

import app.site.model.common.XMLUtil;
import app.site.model.receive.ReceivedMessage;
import app.site.model.reply.ReplyingMessage;
import app.site.service.messagehandler.MessageHandlerFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ChatService {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);
    @Autowired
    MessageHandlerFactory messageHandlerFactory;

    public byte[] chat(String body) {
        ReplyingMessage replyingMessage = messageHandlerFactory.handle(body);
        return replyMessage(replyingMessage);
    }

    private ReceivedMessage receiveMessage(String body) {
        return XMLUtil.toEntity(body, ReceivedMessage.class);
    }

    private byte[] replyMessage(ReplyingMessage replyingMessage) {
        if (replyingMessage == null) return new byte[]{}; // maybe just return 200
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ReplyingMessage.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            byteArrayOutputStream = new ByteArrayOutputStream(1024 * 1000); // maxSize = 1kb
            marshaller.marshal(replyingMessage, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    logger.warn("Failed to close ByteArrayOutputStream", e);
                }
            }
        }
    }
}
