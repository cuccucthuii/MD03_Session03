package com.example.session03.controller;

import com.example.session03.model.entity.Course;
import com.example.session03.service.ICourseService;
import com.example.session03.until.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Course>>> getAllCourses() {
        List<Course> courses = courseService.findAllCourses();
        // Wrapper the course data in the API Response
        ApiResponse<List<Course>> response = new ApiResponse<>(true, "Find All Course Data Successful", courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> getCourseById(@PathVariable Integer id) {
        try {
            Course course = courseService.findCourseById(id);
            ApiResponse<Course> response = new ApiResponse<>(true, "Find Course Data", course);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 FIND successful
        } catch (RuntimeException e) {
            ApiResponse<Course> response = new ApiResponse<>(false, "Course Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404
        } // Dùng try/ catch để bắt ngoại lệ runtime
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Course>> createCourse(@RequestBody Course request) {
        try {
            Course course = courseService.createCourse(request);
            ApiResponse<Course> response = new ApiResponse<>(true, "Course Created", course);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 create successful
        } catch (RuntimeException e) {
            ApiResponse<Course> response = new ApiResponse<>(false, "Course Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 valid data
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> updateCourse(@RequestBody Course request, @PathVariable Integer id) {
        try {
            Course course = courseService.findCourseById(id);
            course = courseService.updateCourse(request, id);
            ApiResponse<Course> response = new ApiResponse<>(true, "Course Updated", course);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 Update successful
        } catch (RuntimeException e) {
            ApiResponse<Course> response = new ApiResponse<>(false, "Course Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 ID NOT FOUND
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Course>> deleteCourse(@PathVariable Integer id) {
        try {
            Course course = courseService.findCourseById(id);
            course = courseService.deleteCourse(id);
            ApiResponse<Course> response = new ApiResponse<>(true, "Course Deleted", course);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 DELETE SUCCESSFUL

        } catch (RuntimeException e) {
            ApiResponse<Course> response = new ApiResponse<>(false, "Course Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 NOT FOUND
        }
    }

}
