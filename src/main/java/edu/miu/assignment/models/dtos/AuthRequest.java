package edu.miu.assignment.models.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "username is required.")
    private String username;
    @NotBlank(message = "password is required.")
    private String password;
}
