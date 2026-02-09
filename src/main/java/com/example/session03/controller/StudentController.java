package com.example.session03.controller;

import com.example.session03.model.dto.StudentResponse;
import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Student;
import com.example.session03.service.IStudentService;
import com.example.session03.until.ApiResponse;
import com.example.session03.until.CourseStatus;
import com.example.session03.until.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private IStudentService studentService;

    @PostMapping
    public ResponseEntity<ApiResponse<Student>> createStudent(@RequestBody Student request){
        try {
            Student st = studentService.createStudent(request);
            ApiResponse<Student> response = new ApiResponse<>(true, "Course Created", st);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 create successful
        } catch (RuntimeException e) {
            ApiResponse<Student> response = new ApiResponse<>(false, "Course Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 valid data
        }
    }

    @GetMapping
    public ResponseEntity<ApiResponse<PageResponse<StudentResponse>>> getAllStudent(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) Sort.Direction direction,
            @RequestParam(required = false) String keyword
    ) {
        PageResponse<StudentResponse> pages = studentService.getPagedStudentsByKeyword(page,size,sortBy,direction,keyword);
        ApiResponse<PageResponse<StudentResponse>> response = new ApiResponse<>(true, "Find All Course Data", pages);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
