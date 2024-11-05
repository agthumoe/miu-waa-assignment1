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
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public PostDto createPost(@RequestBody PostCreateDto post) {
        User user = this.authService.getCurrentUser();
        return this.postService.create(user.getId(), post);
    }

    @GetMapping("{id}")
    public PostDto getPostById(@PathVariable long id) {
         return this.postService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable long id) {
        this.postService.delete(id);
    }

    @PutMapping("{id}")
    public PostDto updatePost(@PathVariable long id, @RequestBody PostCreateDto post) {
        return this.postService.update(id, post);
    }

    @PostMapping("{postId}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto createPost(@PathVariable long postId, @RequestBody CommentCreateDto dto) {
        return this.commentService.save(postId, dto);
    }

    @GetMapping("{postId}/comments")
    public List<CommentDto> getPostComments(@PathVariable long postId) {
        return this.commentService.findByPostId(postId);
    }
}
