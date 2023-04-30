package app.site.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author Steve Zou
 */
@Configuration
public class ResourceConfig {
    @Value("${resource.prefix}")
    public String resourcePrefix;
}
