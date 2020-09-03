package ru.ssu.solution.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ssu.solution.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
