package edu.miu.assignment.repositories;

import edu.miu.assignment.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where size(u.posts) > :size")
    List<User> findAllUsersHavingPostGreaterThan(int size);
}
