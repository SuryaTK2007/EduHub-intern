package com.eduhub.eduhub_backend.component;

import com.eduhub.eduhub_backend.model.Student;
import com.eduhub.eduhub_backend.repository.StudentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(int id) {
        return studentRepository.findById(id);
    }

    public Student addStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student updated) {
        updated.setId(id);
        return studentRepository.save(updated);
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
