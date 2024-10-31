package edu.miu.assignment1.services;

import edu.miu.assignment1.models.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findAll();
    CommentDto findById(long id);
    CommentDto save(long postId, CommentDto dto);
    void delete(long id);
    CommentDto update(long id, CommentDto dto);
}
