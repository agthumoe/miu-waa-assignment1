package edu.miu.assignment.services;

import edu.miu.assignment.models.dtos.*;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse refresh(RefreshTokenRequest request);
    UserDto register(RegistrationRequest registrationRequest);
}
