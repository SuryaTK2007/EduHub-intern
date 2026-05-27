package com.eduhub.eduhub_backend.component;

import com.eduhub.eduhub_backend.model.Course;
import com.eduhub.eduhub_backend.repository.CourseRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }
}
