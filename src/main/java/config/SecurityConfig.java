package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring Security Filter Chain");

        http
                .authorizeRequests(authorizeRequests -> {
                    logger.info("Permitting all requests");
                    authorizeRequests.anyRequest().permitAll();
                })
                .csrf(csrf -> {
                    logger.info("Disabling CSRF");
                    csrf.disable();
                })
                .formLogin(formLogin -> {
                    logger.info("Disabling form login");
                    formLogin.disable();
                })
                .httpBasic(httpBasic -> {
                    logger.info("Disabling HTTP Basic authentication");
                    httpBasic.disable();
                });

        return http.build();
    }
}
