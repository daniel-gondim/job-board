package config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring Security Filter Chain");

        http
                .cors()
                .and()
                .authorizeRequests(authorizeRequests -> {
                    logger.info("Configuring request authorization");
                    authorizeRequests
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .requestMatchers("/user/**").hasRole("USER")
                            .requestMatchers("/public/**").permitAll()
                            .requestMatchers("/vagas").permitAll()
                            .requestMatchers("/login").permitAll()
                            .anyRequest().authenticated();
                })
                .csrf(csrf -> {
                    logger.info("Enabling CSRF");
                    csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
                })
                .formLogin(formLogin -> {
                    logger.info("Enabling form login");
                    formLogin
                            .loginPage("/login")
                            .permitAll();
                })
                .httpBasic(httpBasic -> {
                    logger.info("Enabling HTTP Basic authentication");
                    httpBasic.realmName("MyApp");
                })
                .oauth2Login(oauth2 -> {
                    logger.info("Enabling OAuth2 login");
                    oauth2.loginPage("/oauth2/authorization/google");
                });
        return http.build();
    }
}
