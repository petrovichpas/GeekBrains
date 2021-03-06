package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByName(String name);
    Optional<User> findUserByName(String username);
}
