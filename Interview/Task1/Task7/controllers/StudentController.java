package ru.geek.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.geek.spring.entites.Student;
import ru.geek.spring.services.StudentService;
import java.util.List;

@Controller
public class StudentController {
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/edit/{id}")
    public String editStudentForm(Model model, @PathVariable Long id) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        return "edit_student";
    }

    @PostMapping("/edit")
    public String saveStudent(@ModelAttribute(name = "student") Student student) {
        studentService.save(student);
        return "redirect:/";
    }

    @GetMapping("/students")
    public String showStudents(Model model) {
        List<Student> students = studentService.findAll();
        model.addAttribute("students", students);
        return "students";
    }
}
