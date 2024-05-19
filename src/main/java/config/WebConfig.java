package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Permitir CORS para todos os endpoints
                .allowedOrigins("http://localhost:4200") // Permitir apenas requisições originadas do localhost:4200
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Permitir os métodos HTTP especificados
                .allowCredentials(true); // Permitir credenciais (por exemplo, cookies)
    }
}
