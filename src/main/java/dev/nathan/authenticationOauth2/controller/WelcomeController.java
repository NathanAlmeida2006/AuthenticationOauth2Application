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
