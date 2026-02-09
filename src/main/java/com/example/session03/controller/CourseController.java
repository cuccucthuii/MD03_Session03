package com.example.session03.controller;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseResponseV2;
import com.example.session03.model.dto.CourseUpdateRequest;
import com.example.session03.model.entity.Course;
import com.example.session03.service.ICourseService;
import com.example.session03.until.ApiResponse;
import com.example.session03.until.CourseStatus;
import com.example.session03.until.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @GetMapping("/pages01")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCoursesByPage01(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "courseId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
           PageResponse<CourseResponse> pages = courseService.getPagedCourses(page, size, sortBy, direction);
           ApiResponse<PageResponse<CourseResponse>> response = new ApiResponse<>(true, "Find All Course Data", pages);
           return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pages02")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponseV2>>> getAllCoursesByPage02(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "courseId") String sortBy,
            @RequestParam(defaultValue = "asc") String direction,
            @RequestParam(defaultValue = "ACTIVE") CourseStatus status
    ) {
        PageResponse<CourseResponseV2> pages = courseService.getPagedCoursesByStatusV2(page,size,sortBy,direction, status);
        ApiResponse<PageResponse<CourseResponseV2>> response = new ApiResponse<>(true, "Find All Course Data", pages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/pages03")
    public ResponseEntity<ApiResponse<PageResponse<CourseResponse>>> getAllCoursesByPage03(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String direction,
            @RequestParam(required = false) CourseStatus status,
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<CourseResponse> pages = courseService.getPagedCourse(page,size,sortBy,direction, status, keyword);
        ApiResponse<PageResponse<CourseResponse>> response = new ApiResponse<>(true, "Find All Course Data", pages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
