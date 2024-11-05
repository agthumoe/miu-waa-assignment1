package edu.miu.assignment.models.dtos;

import lombok.Data;

@Data
public class RegistrationRequest {
    private String name;
    private String username;
    private String password;
}
