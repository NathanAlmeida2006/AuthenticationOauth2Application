# OAuth2 Authentication Application

## Overview

This application consists of a security configuration class, a controller with both public and restricted routes, and basic OAuth2 setup. The `/welcome` endpoint is accessible to everyone, while the `/restrictedArea` endpoint requires authentication, which can be provided via OAuth2 login or form login.

## Features

- Public and private endpoints with access control.
- OAuth2 login using third-party providers (e.g., Google).
- Basic form-based login for fallback authentication.
- Security configuration based on Spring Security.

## Configuration

The security configuration is handled by the `SecurityConfiguration` class:

```java
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
```

### OAuth2 Configuration

To use OAuth2 with a provider like Google, you need to configure the client ID and secret in the `application.properties`:

```
spring.application.name=authenticationOauth2

spring.security.oauth2.client.registration.google.client-id=YOUR_GOOGLE_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_GOOGLE_CLIENT_SECRET
```

Replace `YOUR_GOOGLE_CLIENT_ID` and `YOUR_GOOGLE_CLIENT_SECRET` with the actual values from your Google Cloud Console.

## Endpoints

The application defines two endpoints in the `WelcomeController` class:

```java
package dev.nathan.authenticationOauth2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller responsible for handling HTTP requests related to welcome messages.
 * Provides both public and restricted endpoints.
 */
@RestController
public class WelcomeController {

    /**
     * Public endpoint that returns a welcome greeting.
     *
     * @return A welcome message "Good Morning Bot!".
     */
    @GetMapping("/welcome")
    public String welcome() {
        return "Good Morning Bot!";
    }

    /**
     * Private endpoint that returns a personalized greeting.
     * This resource is protected and requires authentication.
     *
     * @return A welcome message "Good Morning Human!".
     */
    @GetMapping("/restrictedArea")
    private String restrictedArea() {
        return "Good Morning Human!";
    }
}
```

- **`/welcome`**: Publicly accessible, returns "Good Morning Bot!".
- **`/restrictedArea`**: Requires authentication, returns "Good Morning Human!".

## Running the Application

### Prerequisites

- Java 17 or higher.
- Maven or Gradle for dependency management.

### Steps

1. Clone the repository or download the source code.
2. Navigate to the project directory.
3. Set your Google OAuth2 client ID and secret in the `application.properties` file.
4. Run the following command to start the application:

   ```bash
   mvn spring-boot:run
   ```

5. Access the endpoints at:

   - Public: `http://localhost:8080/welcome`
   - Restricted: `http://localhost:8080/restrictedArea` (requires login)

## Technologies Used

- **Spring Boot**: Framework for building Java-based web applications.
- **Spring Security**: Provides authentication and authorization support.
- **OAuth2**: For social login with providers like Google.
- **Maven**: For dependency management.

## Contact:

If you have any questions or suggestions, feel free to open an issue on the GitHub repository or send an email to:  
[nathanfelipi2006@gmail.com].

---

Developed with ❤️ by [Nathan Felipi Almeida].
