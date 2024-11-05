package edu.miu.assignment.services.impl;

import edu.miu.assignment.exceptions.HttpStatusException;
import edu.miu.assignment.models.dtos.AuthRequest;
import edu.miu.assignment.models.dtos.AuthResponse;
import edu.miu.assignment.repositories.UserRepository;
import edu.miu.assignment.securities.JwtService;
import edu.miu.assignment.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = this.userRepository
                .findByUsernameIgnoreCase(authRequest.getUsername())
                .orElseThrow(() -> new HttpStatusException("User: " + authRequest.getUsername() + " not found", HttpStatus.NOT_FOUND));
        String accessToken = this.jwtService.generateAccessToken(userDetails.getUsername());
        String refreshToken = this.jwtService.generateRefreshToken(userDetails.getUsername());
        return new AuthResponse(accessToken, refreshToken);
    }
}
