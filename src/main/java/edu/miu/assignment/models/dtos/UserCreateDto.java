package edu.miu.assignment.models.dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserCreateDto {
    private String name;
    private String username;
    private String password;
    private List<String> roles = new ArrayList<>();
}
