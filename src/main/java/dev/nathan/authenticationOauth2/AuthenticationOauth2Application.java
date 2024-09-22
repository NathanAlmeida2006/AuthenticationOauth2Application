package dev.nathan.authenticationOauth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the OAuth2 authentication application.
 * Sets up and starts the Spring Boot application.
 */
@SpringBootApplication
public class AuthenticationOauth2Application {

	/**
	 * Main method to launch the application.
	 *
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationOauth2Application.class, args);
	}
}
