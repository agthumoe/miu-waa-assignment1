package edu.miu.assignment1.services;

import edu.miu.assignment1.models.PostCreateDto;
import edu.miu.assignment1.models.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();
    List<PostDto> findByAuthor(String author);
    List<PostDto> findByAuthorContaining(String author);
    PostDto findById(long id);
    PostDto save(PostCreateDto post);
    void delete(long id);
    PostDto update(long id, PostCreateDto post);
}
