package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.*;
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
    public List<UserDto> getAllUsers(
            @RequestParam(name = "posts:gt", required = false, defaultValue = "0") int size,
            @RequestParam(name = "posts:title", required = false) String postTitle) {
        if (size > 0) {
            return this.userService.findAllUsersHavingPostGreaterThan(size);
        }
        if (postTitle != null && !postTitle.isEmpty()) {
            return this.userService.findAllUsersThatMadePostsWithinGivenTitle(postTitle);
        }
        return this.userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto getUserById(@PathVariable long id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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

    @PostMapping("{userId}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@PathVariable long userId, @RequestBody PostCreateDto post) {
        return this.postService.create(userId, post);
    }

    @GetMapping("{userId}/posts/{postId}")
    public PostDto findByUserIdAndPostId(@PathVariable long userId, @PathVariable long postId) {
        return this.userService.findByUserIdAndPostId(userId, postId);
    }

    @GetMapping("{userId}/posts/{postId}/comments")
    public List<CommentDto> findCommentsByUserIdPostId(@PathVariable long userId, @PathVariable long postId) {
        return this.userService.findAllCommentsByUserIdAndPostId(userId, postId);
    }

    @GetMapping("{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentByUserIdPostIdAndCommentId(@PathVariable long userId, @PathVariable long postId, @PathVariable long commentId) {
        return this.userService.getCommentsByUserIdAndPostId(userId, postId, commentId);
    }
}
