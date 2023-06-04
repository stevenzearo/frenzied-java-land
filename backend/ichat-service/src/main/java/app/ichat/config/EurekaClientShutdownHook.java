package app.ichat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.stereotype.Component;

/**
 * @author Steve Zou
 */
@Component
public class EurekaClientShutdownHook implements ApplicationListener<ContextClosedEvent> {
    private final Logger logger = LoggerFactory.getLogger(EurekaClientShutdownHook.class);
    @Autowired
    EurekaClientManager eurekaClientManager;

    @Override
    public void onApplicationEvent(ContextClosedEvent event) {
        eurekaClientManager.offlineAll();
        logger.info("Successfully shutdown all eureka clients.");
    }
}
