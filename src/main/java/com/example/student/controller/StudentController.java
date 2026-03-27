package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;
import com.example.student.dto.StudentDTO;
import com.example.student.dto.StudentResponseDTO;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // GET /students
    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }

    // GET /students/{id}
    @GetMapping("/{id}")
    public Student getStudentById(@PathVariable("id") int id) {
        return studentService.getStudentById(id);
    }

    // POST /students
    @PostMapping
    public StudentResponseDTO setStudent(@Valid@RequestBody StudentDTO dto) {
        Student s = new Student();
        s.setName(dto.getName());
        s.setDepartment(dto.getDepartment());
        Student saved = studentService.setStudent(s);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setDepartment(saved.getDepartment());

        return response;
    }

    // DELETE /students/{id}
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        studentService.deleteStudent(id);
    }
}

