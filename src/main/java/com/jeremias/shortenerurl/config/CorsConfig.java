package com.jeremias.shortenerurl.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Value("${server.url}")
    private String serverUrl;
    private final String CORS_MAPPING = "/**";
    private final String[] CORS_ORIGEN = {serverUrl};
    private final String[] CORS_METHOD = {"GET", "POST"};
    private final String[] CORS_HEADERS = {"*"};

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping(CORS_MAPPING)
                .allowedOrigins(CORS_ORIGEN)
                .allowedMethods(CORS_METHOD)
                .allowedHeaders(CORS_HEADERS);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of(CORS_ORIGEN));
        configuration.setAllowedMethods(Arrays.asList(CORS_METHOD));
        configuration.setAllowedHeaders(Arrays.asList(CORS_HEADERS));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(CORS_MAPPING, configuration);
        return source;
    }
}