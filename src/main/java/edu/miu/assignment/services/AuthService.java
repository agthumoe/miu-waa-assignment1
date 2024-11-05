package edu.miu.assignment.services;

import edu.miu.assignment.models.User;
import edu.miu.assignment.models.dtos.*;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
    AuthResponse refresh(RefreshTokenRequest request);
    UserDto register(RegistrationRequest registrationRequest);
    UserDto getProfile();
    void updatePassword(long userId, String newPassword);
    User getCurrentUser();
}
