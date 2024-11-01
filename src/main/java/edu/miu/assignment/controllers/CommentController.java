package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.CommentCreateDto;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments() {
        return this.commentService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable long id) {
        return this.commentService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        this.commentService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentDto update(@PathVariable long id, @RequestBody CommentCreateDto dto) {
        return this.commentService.update(id, dto);
    }
}
