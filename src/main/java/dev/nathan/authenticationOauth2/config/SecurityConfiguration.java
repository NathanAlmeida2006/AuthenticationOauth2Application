package dev.nathan.authenticationOauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for the application.
 * Defines authorization and authentication rules, as well as OAuth2 and form login settings.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    /**
     * Configures the security filter chain to control access to routes.
     * The "/welcome" route is public, while other routes require authentication.
     *
     * @param httpSecurity The HTTP security configuration provided by Spring Security.
     * @return The configured security filter chain.
     * @throws Exception If an error occurs while setting up security.
     */
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/welcome").permitAll();  // Allows public access to the "/welcome" route
                    auth.anyRequest().authenticated();  // Requires authentication for any other route
                }).oauth2Login(Customizer.withDefaults())  // Configures OAuth2 login
                .formLogin(Customizer.withDefaults())  // Configures form-based login
                .build();
    }
}
