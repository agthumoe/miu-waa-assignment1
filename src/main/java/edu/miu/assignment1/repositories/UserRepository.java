package edu.miu.assignment1.repositories;

import edu.miu.assignment1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
