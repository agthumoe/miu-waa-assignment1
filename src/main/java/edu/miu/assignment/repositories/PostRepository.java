package edu.miu.assignment.repositories;

import edu.miu.assignment.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String author);
    List<Post> findByAuthorContaining(String author);
    List<Post> findByTitle(String title);
}
