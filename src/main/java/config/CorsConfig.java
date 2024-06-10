package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    private static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

    @Value("${app.cors.allowed-origins}")
    private String allowedOrigins;

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configAutenticacao = getCorsConfiguration();
        // source.registerCorsConfiguration("/oauth/token", configAutenticacao);
        source.registerCorsConfiguration("/**", configAutenticacao); // Global para todas as URLs da aplicação

        CorsFilter corsFilter = new CorsFilter(source);

        return corsFilter;

    }

    private CorsConfiguration getCorsConfiguration() {
        CorsConfiguration configAutenticacao = new CorsConfiguration();
        configAutenticacao.setAllowCredentials(true);
        configAutenticacao.addAllowedOrigin(allowedOrigins);
        configAutenticacao.addAllowedHeader("Authorization");
        configAutenticacao.addAllowedHeader("Content-Type");
        configAutenticacao.addAllowedHeader("Accept");
        configAutenticacao.addAllowedMethod("POST");
        configAutenticacao.addAllowedMethod("GET");
        configAutenticacao.addAllowedMethod("DELETE");
        configAutenticacao.addAllowedMethod("PUT");
        configAutenticacao.addAllowedMethod("OPTIONS");
        configAutenticacao.setMaxAge(3600L);
        return configAutenticacao;
    }
}