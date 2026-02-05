package com.example.session03.controller;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseUpdateRequest;
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
    public ResponseEntity<ApiResponse<List<CourseResponse>>> getAllCourses() {
        List<CourseResponse> courses = courseService.findAllCourses();
        // Wrapper the course data in the API Response
        ApiResponse<List<CourseResponse>> response = new ApiResponse<>(true, "Find All Course Data Successful", courses);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> getCourseById(@PathVariable Integer id) {
            CourseResponse course = courseService.findCourseById(id);
            ApiResponse<CourseResponse> response = new ApiResponse<>(true, "Find Course Data", course);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 FIND successful
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CourseResponse>> createCourse(@RequestBody CourseCreateRequest request) {
            CourseResponse course = courseService.createCourse(request);
            ApiResponse<CourseResponse> response = new ApiResponse<>(true, "Course Created", course);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 create successful
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CourseResponse>> updateCourse(@RequestBody CourseUpdateRequest request, @PathVariable Integer id) {
            CourseResponse course = courseService.updateCourse(request, id);
            ApiResponse<CourseResponse> response = new ApiResponse<>(true, "Course Updated", course);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 Update successful
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseService.deleteCourse(id);

        }
}
