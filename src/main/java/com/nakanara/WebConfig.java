package com.nakanara;

import ch.qos.logback.access.tomcat.LogbackValve;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ResourceLoader;
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

    private ResourceLoader resourceLoader;

    @Autowired
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Bean
    public WebServerFactoryCustomizer embeddedServletContainerCustomizer() {
        return container -> {
            if (container instanceof TomcatServletWebServerFactory) {
                ((TomcatServletWebServerFactory) container).addContextCustomizers((TomcatContextCustomizer) context -> {
                    LogbackValve valve = new LogbackValve();
                    valve.setFilename(resourceLoader.getResource(ResourceLoader.CLASSPATH_URL_PREFIX + "logback.xml").getFilename());
                    context.getPipeline().addValve(valve);
                });
            }
        };
    }

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
