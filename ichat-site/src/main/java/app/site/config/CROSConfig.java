package app.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Steve Zou
 */
@Configuration
public class CROSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/hland/**")
            .allowedOriginPatterns("*localhost*", "*fjavaland*");
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
}
