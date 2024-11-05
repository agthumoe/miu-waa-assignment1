package edu.miu.assignment.models.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private long id;
    private String name;
    private String username;
    private List<String> roles;
}
