package edu.miu.assignment1.services;

import edu.miu.assignment1.models.dtos.UserCreateDto;
import edu.miu.assignment1.models.dtos.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();
    UserDto findById(long id);
    UserDto save(UserCreateDto post);
    void delete(long id);
    UserDto update(long id, UserCreateDto post);
}
