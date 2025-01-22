package com.cg.repositories;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.cg.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);


}
