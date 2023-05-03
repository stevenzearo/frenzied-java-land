package app.site.model.common;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Steve Zou
 */
public class XMLUtil {
    private static final Logger logger = LoggerFactory.getLogger(XMLUtil.class);

    public static <T> T toEntity(String xmlContent, Class<T> clazz) {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            byteArrayInputStream = new ByteArrayInputStream(xmlContent.getBytes(StandardCharsets.UTF_8));
            StreamSource inputSource = new StreamSource(byteArrayInputStream);
            JAXBElement<T> unmarshal = unmarshaller.unmarshal(inputSource, clazz);
            return unmarshal.getValue();
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
    }
}
