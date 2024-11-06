package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.*;
import edu.miu.assignment.services.AuthService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthService authService;

    @PostMapping("/authenticate")
    public AuthResponse authenticate(@RequestBody @Valid AuthRequest authRequest) {
        return this.authService.authenticate(authRequest);
    }

    @PostMapping("/refresh")
    @SecurityRequirement(name = "bearerAuth")
    public AuthResponse refreshToken(@RequestBody @Valid RefreshTokenRequest request) {
        return this.authService.refresh(request);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody @Valid RegistrationRequest request) {
        return this.authService.register(request);
    }

    @GetMapping("/profile")
    @SecurityRequirement(name = "bearerAuth")
    public UserDto getProfile() {
        return this.authService.getProfile();
    }
}
