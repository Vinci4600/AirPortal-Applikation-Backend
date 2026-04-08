package org.example.projektarbeit_modul295_vincent_diergardt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public static final String URL = "http://localhost:5173";

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        String[] allowedMethods = {"GET", "POST", "PUT", "DELETE", "OPTIONS"};
        registry.addMapping("/api/aircrafts/**")
                .allowedOrigins(URL) // <- Vite dev Server
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/airports/**")
                .allowedOrigins(URL) // <- Vite dev Server
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/bookings/**")
                .allowedOrigins(URL) // <- Vite dev Server
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/flights/**")
                .allowedOrigins(URL) // <- Vite dev Server
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/logistic_users/**")
                .allowedOrigins(URL) // <- Vite dev Server
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/passengers/**")
                .allowedOrigins(URL) // <- Vite dev Server
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
        registry.addMapping("/api/auth/**") // Added CORS mapping for authentication endpoints
                .allowedOrigins(URL)
                .allowedMethods(allowedMethods)
                .allowedHeaders("*")
                .allowCredentials(false)
                .maxAge(3600);
    }
}
