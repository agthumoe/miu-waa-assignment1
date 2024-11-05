package edu.miu.assignment.services;

import edu.miu.assignment.models.dtos.AuthRequest;
import edu.miu.assignment.models.dtos.AuthResponse;

public interface AuthService {
    AuthResponse authenticate(AuthRequest authRequest);
}
