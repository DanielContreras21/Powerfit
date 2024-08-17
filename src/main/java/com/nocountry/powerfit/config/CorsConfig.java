package com.nocountry.powerfit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("https://power-fit-2023.netlify.app");
        config.addAllowedOrigin("https://power-fit-v3.netlify.app");
        config.addAllowedOrigin("http://localhost:4200"); // Agrega aquí los orígenes permitidos
        config.addAllowedOrigin("https://powerfit-app.azurewebsites.net/");
        config.addAllowedHeader("*"); // Permite cualquier cabecera
        config.addAllowedMethod("*"); // Permite cualquier método (GET, POST, etc.)

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }
}
