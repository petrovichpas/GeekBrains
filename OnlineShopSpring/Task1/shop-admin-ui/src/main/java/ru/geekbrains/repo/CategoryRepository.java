package ru.geekbrains.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
