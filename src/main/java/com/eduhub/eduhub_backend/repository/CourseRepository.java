package com.eduhub.eduhub_backend.repository;

import com.eduhub.eduhub_backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByTitleContainingIgnoreCase(String title);
    List<Course> findByInstructorContainingIgnoreCase(String instructor);
}
