package com.eduhub.eduhub_backend.component;

import com.eduhub.eduhub_backend.exception.BadRequestException;
import com.eduhub.eduhub_backend.exception.ResourceNotFoundException;
import com.eduhub.eduhub_backend.model.Course;
import com.eduhub.eduhub_backend.repository.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(int id) {
        return Optional.of(courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id)));
    }

    public Course addCourse(Course course) {
        if (course.getTitle() == null || course.getTitle().isBlank())
            throw new BadRequestException("Course title must not be empty");
        return courseRepository.save(course);
    }

    public Course updateCourse(int id, Course updated) {
        courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        if (updated.getTitle() == null || updated.getTitle().isBlank())
            throw new BadRequestException("Course title must not be empty");
        updated.setId(id);
        return courseRepository.save(updated);
    }

    public void deleteCourse(int id) {
        courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        courseRepository.deleteById(id);
    }

    public List<Course> searchByTitle(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Course> searchByInstructor(String instructor) {
        return courseRepository.findByInstructorContainingIgnoreCase(instructor);
    }
}
