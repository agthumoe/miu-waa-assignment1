package edu.miu.assignment.models.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserCreateDto {
    private String name;
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    private List<String> roles = new ArrayList<>();
}
