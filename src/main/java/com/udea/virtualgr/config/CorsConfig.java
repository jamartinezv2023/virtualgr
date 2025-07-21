package com.udea.virtualgr.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/graphql") // o "/**" si quieres habilitar todo
                        .allowedOrigins("http://localhost:5173") // puerto del frontend Vite
                        .allowedMethods("*")
                        .allowedHeaders("*");
            }
        };
    }
}
