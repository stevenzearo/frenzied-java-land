package app.site.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Steve Zou
 */
@Configuration
public class CROSConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
//            .allowedOriginPatterns("*")
            .allowedMethods(HttpMethod.GET.name(), HttpMethod.PUT.name(),
                HttpMethod.POST.name(), HttpMethod.PATCH.name(), HttpMethod.OPTIONS.name());
        WebMvcConfigurer.super.addCorsMappings(registry);
    }
/*
    @Bean
    public FilterRegistrationBean<CROSFilter> crosFilter() {
        FilterRegistrationBean<CROSFilter> registrationBean = new FilterRegistrationBean<>();
        CROSFilter crosFilter = new CROSFilter();
        registrationBean.setFilter(crosFilter);
        registrationBean.setOrder(1);
        return registrationBean;
    }*/
}
