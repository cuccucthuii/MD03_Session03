package com.example.session03.controller;

import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Student;
import com.example.session03.service.IStudentService;
import com.example.session03.until.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
