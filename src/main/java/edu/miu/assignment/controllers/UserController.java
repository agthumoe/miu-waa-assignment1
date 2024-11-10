package edu.miu.assignment.controllers;

import edu.miu.assignment.aspects.ExecutionTime;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.models.dtos.UserCreateDto;
import edu.miu.assignment.models.dtos.UserDto;
import edu.miu.assignment.services.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto createUser(@RequestBody @Valid UserCreateDto user) {
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
    @ExecutionTime
    public UserDto getUserById(@PathVariable("id") @Min(1) long id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable("id") @Min(1) long id) {
        this.userService.delete(id);
    }

    @PutMapping("{id}")
    public UserDto updateUser(@PathVariable("id") @Min(1) long id, @Valid @RequestBody UserCreateDto user) {
        return this.userService.update(id, user);
    }

    @GetMapping("{userId}/posts")
    public List<PostDto> findPostsByUserId(@PathVariable("userId") @Min(1) long userId) {
        return this.userService.findAllPostsByUserId(userId);
    }

    @GetMapping("{userId}/posts/{postId}")
    public PostDto findByUserIdAndPostId(@PathVariable("userId") @Min(1) long userId, @PathVariable @Min(1) long postId) {
        return this.userService.findByUserIdAndPostId(userId, postId);
    }

    @GetMapping("{userId}/posts/{postId}/comments")
    public List<CommentDto> findCommentsByUserIdPostId(@PathVariable("userId") @Min(1) long userId, @PathVariable @Min(1) long postId) {
        return this.userService.findAllCommentsByUserIdAndPostId(userId, postId);
    }

    @GetMapping("{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentByUserIdPostIdAndCommentId(@PathVariable("userId") @Min(1) long userId, @PathVariable("postId") @Min(1) long postId, @PathVariable("commentId") @Min(1) long commentId) {
        return this.userService.getCommentsByUserIdAndPostId(userId, postId, commentId);
    }

    /**
     * This endpoint is only for testing.
     */
    @GetMapping("exceptions")
    public void throwExceptionMethod() {
        throw new RuntimeException("This is exception message for testing purposes");
    }
}
