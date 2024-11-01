package edu.miu.assignment.services;

import edu.miu.assignment.models.dtos.CommentDto;

import java.util.List;

public interface CommentService {
    List<CommentDto> findByPostId(long postId);
    List<CommentDto> findAll();
    CommentDto findById(long id);
    CommentDto save(long postId, CommentDto dto);
    void delete(long id);
    CommentDto update(long id, CommentDto dto);
}
