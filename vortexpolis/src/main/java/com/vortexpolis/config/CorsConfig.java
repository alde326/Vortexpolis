package com.vortexpolis.config;

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
                registry.addMapping("/**") // 👉 Aplica a todas las rutas
                        .allowedOrigins("http://localhost:4200") // 👉 Tu frontend Angular
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 👉 Métodos permitidos
                        .allowedHeaders("*") // 👉 Permite cualquier header
                        .allowCredentials(true); // 👉 Si manejas cookies o tokens en las cabeceras
            }
        };
    }
}
