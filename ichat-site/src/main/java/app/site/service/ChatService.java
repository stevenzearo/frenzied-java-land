package app.site.service;

import app.site.model.Image;
import app.site.model.Location;
import app.site.model.MessageType;
import app.site.model.ReceivedMessage;
import app.site.model.ReplyingMessage;
import app.site.model.Voice;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author Steve Zou
 */
@Service
public class ChatService {
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    public byte[] chat(String body) {
        ReceivedMessage message = receiveMessage(body);
        ReplyingMessage replyingMessage = new ReplyingMessage();
        replyingMessage.fromUserName = message.toUserName;
        replyingMessage.toUserName = message.fromUserName;
        replyingMessage.createTime = System.currentTimeMillis();
        replyingMessage.msgId = UUID.randomUUID().toString();
        MessageType msgType = message.msgType;
        String content = message.content;
        replyingMessage.msgType = msgType;
        if (MessageType.TEXT == msgType) {
            replyingMessage.content = content;
        } else if (MessageType.IMAGE == msgType) {
            Image image = new Image();
            image.mediaId = message.mediaId;
            replyingMessage.media = image;
        } else if (MessageType.VOICE == msgType) {
            Voice voice = new Voice();
            voice.mediaId = message.mediaId;
            replyingMessage.media = voice;
        } else {
            logger.warn("Unsupported media type");
            return "".getBytes();
        }
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
