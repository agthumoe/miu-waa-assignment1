package edu.miu.assignment.repositories;

import edu.miu.assignment.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("select c from User u inner join u.posts p inner join p.comments c where u.id = :userId and p.id = :postId and c.id = :commentId")
    Optional<Comment> findByUserIdPostIdAndCommentId(long userId, long postId, long commentId);
}
