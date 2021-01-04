package dao;

import models.Student;
import java.util.List;

public interface StudentDao {
    Student findById(int id);
    void save(Student student);
    void update(Student student);
    void delete(Student student);
    List<Student> findAll();
}
