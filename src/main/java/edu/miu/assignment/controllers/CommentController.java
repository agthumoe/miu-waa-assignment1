package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.CommentCreateDto;
import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@SecurityRequirement(name = "bearerAuth")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments() {
        return this.commentService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable @Min(1) long id) {
        return this.commentService.findById(id);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable @Min(1) long id) {
        this.commentService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentDto update(@PathVariable @Min(1) long id, @RequestBody @Valid CommentCreateDto dto) {
        return this.commentService.update(id, dto);
    }
}
