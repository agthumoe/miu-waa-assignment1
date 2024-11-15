package edu.miu.assignment.controllers;

import edu.miu.assignment.models.User;
import edu.miu.assignment.models.dtos.CommentCreateDto;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.models.dtos.PostCreateDto;
import edu.miu.assignment.models.dtos.PostDto;
import edu.miu.assignment.services.AuthService;
import edu.miu.assignment.services.CommentService;
import edu.miu.assignment.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class PostController {
    private final PostService postService;
    private final CommentService commentService;
    private final AuthService authService;

    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(value = "author", required = false) String author,
            @RequestParam(value = "author:containing", required = false) String authorContaining,
            @RequestParam(value = "title", required = false) String title) {
        if (author != null && !author.isEmpty()) {
            return postService.findByAuthor(author);
        } else if (authorContaining != null && !authorContaining.isEmpty()) {
            return postService.findByAuthorContaining(authorContaining);
        } else if (title != null && !title.isEmpty()) {
            return postService.findByTitle(title);
        }
        return postService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto createPost(@RequestBody @Validated PostCreateDto post) {
        User user = this.authService.getCurrentUser();
        return this.postService.create(user.getId(), post);
    }

    @GetMapping("{id}")
    public PostDto getPostById(@PathVariable("id") @Min(1) long id) {
        return this.postService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable("id") @Min(1) long id) {
        this.postService.delete(id);
    }

    @PutMapping("{id}")
    public PostDto updatePost(@PathVariable("id") @Min(1) long id, @RequestBody @Valid PostCreateDto post) {
        return this.postService.update(id, post);
    }

    @PostMapping("{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createPost(@PathVariable("postId") @Min(1) long postId, @RequestBody @Valid CommentCreateDto dto) {
        return this.commentService.save(postId, dto);
    }

    @GetMapping("/{id}/comments")
    public List<CommentDto> getPostComments(@PathVariable("id") long id) {
        return this.commentService.findByPostId(id);
    }
}
