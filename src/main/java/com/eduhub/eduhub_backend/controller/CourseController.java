package com.eduhub.eduhub_backend.controller;

import com.eduhub.eduhub_backend.component.CourseService;
import com.eduhub.eduhub_backend.model.Course;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAllCourses();
    }

    @GetMapping("/{id}")
    public Course getCourseById(@PathVariable int id) {
        return courseService.getCourseById(id).get();
    }

    @GetMapping("/search")
    public ResponseEntity<List<Course>> searchCourses(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String instructor) {
        if (title != null) return ResponseEntity.ok(courseService.searchByTitle(title));
        if (instructor != null) return ResponseEntity.ok(courseService.searchByInstructor(instructor));
        return ResponseEntity.badRequest().build();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable int id, @RequestBody Course course) {
        return courseService.updateCourse(id, course);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
    }
}
