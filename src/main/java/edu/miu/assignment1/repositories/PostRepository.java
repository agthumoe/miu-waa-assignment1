package edu.miu.assignment1.repositories;

import edu.miu.assignment1.models.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    List<Post> findAll();

    List<Post> findByAuthor(String author);

    List<Post> findByAuthorContaining(String author);

    Optional<Post> findById(long id);

    Post save(Post post);

    void delete(long id);

    Post update(long id, Post post);
}
