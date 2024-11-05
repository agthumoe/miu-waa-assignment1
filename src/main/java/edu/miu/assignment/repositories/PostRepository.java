package edu.miu.assignment.repositories;

import edu.miu.assignment.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByAuthor(String author);
    List<Post> findByAuthorContaining(String author);
    List<Post> findByTitle(String title);
    @Query("select p from User u inner join u.posts p where u.id = :userId and p.id = :postId")
    Optional<Post> findByUserIdAndPostId(long userId, long postId);
}
