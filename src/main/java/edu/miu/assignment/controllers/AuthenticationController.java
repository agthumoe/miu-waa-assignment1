package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.AuthRequest;
import edu.miu.assignment.models.dtos.AuthResponse;
import edu.miu.assignment.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authenticate")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @PostMapping
    public AuthResponse authenticate(@RequestBody AuthRequest authRequest) {
        LOGGER.info("Authenticating user {}", authRequest.getUsername());
        return this.authService.authenticate(authRequest);
    }
}
