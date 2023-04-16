package app.site.service;

import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author Steve Zou
 */
@Service
public class ChatService {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Autowired
    MessageHandlerFactory messageHandlerFactory;

    public byte[] chat(String body) {
        ReceivedMessage message = receiveMessage(body);
        ReplyingMessage replyingMessage = messageHandlerFactory.get(message.msgType).handle(message);
        return replyMessage(replyingMessage);
    }

    private ReceivedMessage receiveMessage(String body) {
        ReceivedMessage message;
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ReceivedMessage.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            byteArrayInputStream = new ByteArrayInputStream(body.getBytes(StandardCharsets.UTF_8));
            message = (ReceivedMessage) unmarshaller.unmarshal(byteArrayInputStream);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        } finally {
            if (byteArrayInputStream != null) {
                try {
                    byteArrayInputStream.close();
                } catch (IOException e) {
                    logger.warn("Failed to close ByteArrayInputStream", e);
                }
            }
        }
        return message;
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
