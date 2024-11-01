package edu.miu.assignment.controllers;

import edu.miu.assignment.models.dtos.CommentDto;
import edu.miu.assignment.services.CommentService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/comments")
public class CommentController {
    private final CommentService commentService;
    private final ModelMapper mapper;

    @Autowired
    public CommentController(CommentService commentService, ModelMapper mapper) {
        this.commentService = commentService;
        this.mapper = mapper;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDto> getAllComments() {
        return this.mapper.map(this.commentService.findAll(), new TypeToken<List<CommentDto>>() {}.getType());
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public CommentDto getCommentById(@PathVariable long id) {
        return this.mapper.map(this.commentService.findById(id), CommentDto.class);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable long id) {
        this.commentService.delete(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public CommentDto update(@PathVariable long id, @RequestBody CommentDto dto) {
        return this.commentService.update(id, dto);
    }
}
