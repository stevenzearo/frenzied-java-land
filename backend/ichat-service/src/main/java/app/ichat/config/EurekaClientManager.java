package app.ichat.config;

import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Steve Zou
 */
@Component
public class EurekaClientManager {
    @Autowired
    EurekaClient eurekaClient;

    public void offlineAll() {
        eurekaClient.shutdown();
    }
}
