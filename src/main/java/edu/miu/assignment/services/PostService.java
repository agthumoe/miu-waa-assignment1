package edu.miu.assignment.services;

import edu.miu.assignment.models.dtos.PostCreateDto;
import edu.miu.assignment.models.dtos.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    List<PostDto> findByAuthor(String author);
    List<PostDto> findByAuthorContaining(String author);
    List<PostDto> findByTitle(String title);
    PostDto findById(long id);
    PostDto create(long userId, PostCreateDto post);
    void delete(long id);
    PostDto update(long id, PostCreateDto post);
}
