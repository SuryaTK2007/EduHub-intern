package com.eduhub.eduhub_backend.component;

import com.eduhub.eduhub_backend.exception.BadRequestException;
import com.eduhub.eduhub_backend.exception.ResourceNotFoundException;
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
        return Optional.of(studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id)));
    }

    public Student addStudent(Student student) {
        if (student.getName() == null || student.getName().isBlank())
            throw new BadRequestException("Student name must not be empty");
        if (student.getEmail() == null || student.getEmail().isBlank())
            throw new BadRequestException("Student email must not be empty");
        return studentRepository.save(student);
    }

    public Student updateStudent(int id, Student updated) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        if (updated.getName() == null || updated.getName().isBlank())
            throw new BadRequestException("Student name must not be empty");
        if (updated.getEmail() == null || updated.getEmail().isBlank())
            throw new BadRequestException("Student email must not be empty");
        updated.setId(id);
        return studentRepository.save(updated);
    }

    public void deleteStudent(int id) {
        studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
        studentRepository.deleteById(id);
    }
}
