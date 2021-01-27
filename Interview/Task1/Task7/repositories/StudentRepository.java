package ru.geek.spring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geek.spring.entites.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
