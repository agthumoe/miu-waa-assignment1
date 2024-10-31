package edu.miu.assignment1.controllers;

import edu.miu.assignment1.models.dtos.PostDto;
import edu.miu.assignment1.models.dtos.UserCreateDto;
import edu.miu.assignment1.models.dtos.UserDto;
import edu.miu.assignment1.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserDto createUser(@RequestBody UserCreateDto user) {
        return this.userService.save(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers(@RequestParam(name = "posts:gt", required = false) int size) {
        if (size > 0) {
            return this.userService.findAllUsersHavingPostGreaterThan(size);
        }
        return this.userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("{id}")
    public void deleteUserById(@PathVariable long id) {
        this.userService.delete(id);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable long id, @RequestBody UserCreateDto user) {
        return this.userService.update(id, user);
    }

    @GetMapping("{userId}/posts")
    public List<PostDto> findPostsByUserId(@PathVariable long userId) {
        return this.userService.findAllPostsByUserId(userId);
    }
}
