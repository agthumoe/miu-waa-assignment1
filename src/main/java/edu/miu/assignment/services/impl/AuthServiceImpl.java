package edu.miu.assignment.services.impl;

import edu.miu.assignment.exceptions.HttpStatusException;
import edu.miu.assignment.models.Role;
import edu.miu.assignment.models.User;
import edu.miu.assignment.models.dtos.*;
import edu.miu.assignment.others.CustomMapper;
import edu.miu.assignment.repositories.RoleRepository;
import edu.miu.assignment.repositories.UserRepository;
import edu.miu.assignment.securities.JwtService;
import edu.miu.assignment.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final CustomMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public AuthResponse authenticate(AuthRequest authRequest) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        UserDetails userDetails = this.userRepository
                .findByUsernameIgnoreCase(authRequest.getUsername())
                .orElseThrow(() -> new HttpStatusException("User: " + authRequest.getUsername() + " not found", HttpStatus.NOT_FOUND));
        final String accessToken = this.jwtService.generateAccessToken(userDetails.getUsername());
        final String refreshToken = this.jwtService.generateRefreshToken(userDetails.getUsername());
        return new AuthResponse(accessToken, refreshToken);
    }

    @Override
    public AuthResponse refresh(RefreshTokenRequest request) {
        if (this.jwtService.validateRefreshToken(request.getRefreshToken())) {
            final String username = this.jwtService.getUsername(request.getRefreshToken());
            final String accessToken = this.jwtService.generateAccessToken(username);
            final String refreshToken = this.jwtService.generateRefreshToken(username);
            return new AuthResponse(accessToken, refreshToken);
        }
        throw new HttpStatusException("Invalid refresh token", HttpStatus.UNAUTHORIZED);
    }

    @Override
    public UserDto register(RegistrationRequest registrationRequest) {
        User user = this.mapper.map(registrationRequest, User.class);
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        Role userRole = this.roleRepository.findByNameIgnoreCase("user").orElseThrow(() -> new HttpStatusException("Role not found", HttpStatus.NOT_FOUND));
        user.getRoles().add(userRole);
        return this.mapper.map(this.userRepository.save(user), UserDto.class);
    }
}
