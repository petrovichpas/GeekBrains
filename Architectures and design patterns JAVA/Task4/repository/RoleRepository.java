package ru.geekbrains.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.models.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
