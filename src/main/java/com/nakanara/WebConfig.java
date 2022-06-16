package com.nakanara;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@EnableJpaAuditing
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Objects.requireNonNull(registry);

        // could use either '/**/images/{filename:\w+\.png}' or '/**/images/*.png'
//        registry.addResourceHandler("/assets/**")
//                .addResourceLocations("/assets/")
//                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS));

//
//        registry.addResourceHandler("/assets/**")
//                .addResourceLocations("/assets/")
//                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
//                .resourceChain(true)
//                .addResolver(new VersionResourceResolver()
//                .addContentVersionStrategy("/**"));
//
//        registry.addResourceHandler("/js/**")
//                .addResourceLocations("/js/")
//                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
//                .resourceChain(true)
//                .addResolver(new VersionResourceResolver()
//                .addContentVersionStrategy("/**"));

        registry.addResourceHandler("*.css")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(false);

    }

}
