package edu.miu.assignment1.controllers;

import edu.miu.assignment1.models.PostCreateDto;
import edu.miu.assignment1.models.PostDto;
import edu.miu.assignment1.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public List<PostDto> getAllPosts(@RequestParam(value = "author", required = false) String author, @RequestParam(value = "author:containing", required = false) String authorContaining) {
        if (author != null) {
            return postService.findByAuthor(author);
        } else if (authorContaining != null) {
            return postService.findByAuthorContaining(authorContaining);
        }
        return postService.findAll();
    }

    @GetMapping("{id}")
    public PostDto getPostById(@PathVariable long id) {
         return this.postService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostDto savePost(@RequestBody PostCreateDto post) {
        return this.postService.save(post);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePostById(@PathVariable long id) {
        this.postService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public PostDto updatePost(@PathVariable long id, @RequestBody PostCreateDto post) {
        return this.postService.update(id, post);
    }
}
