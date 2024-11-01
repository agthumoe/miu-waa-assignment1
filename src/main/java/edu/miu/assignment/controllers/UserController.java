package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.PostCreateDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.models.dtos.UserCreateDto;
import edu.miu.assignment.models.dtos.UserDto;
import edu.miu.assignment.services.PostService;
import edu.miu.assignment.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody UserCreateDto user) {
        return this.userService.save(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers(@RequestParam(name = "posts:gt", required = false) int size) {
        if (size > 0) {
            return this.userService.findAllUsersHavingPostGreaterThan(size);
        }
        return this.userService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserById(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable long id) {
        this.userService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto updateUser(@PathVariable long id, @RequestBody UserCreateDto user) {
        return this.userService.update(id, user);
    }

    @GetMapping("{userId}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> findPostsByUserId(@PathVariable long userId) {
        return this.userService.findAllPostsByUserId(userId);
    }

    @PostMapping("{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@PathVariable long userId, @RequestBody PostCreateDto post) {
        return this.postService.create(userId, post);
    }
}
