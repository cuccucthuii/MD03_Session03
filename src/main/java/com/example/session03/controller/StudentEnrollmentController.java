package com.example.session03.controller;

import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.service.IStudentEnrollmentService;
import com.example.session03.until.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student-enrollments")
public class StudentEnrollmentController {
    @Autowired
    private IStudentEnrollmentService studentEnrollmentService;

    @PostMapping
    public ResponseEntity<ApiResponse<StudentEnrollment>> enrollStudent(@RequestBody StudentEnrollment request){
        try {
            StudentEnrollment enrollment = studentEnrollmentService.enrollStudent(request.getStudent().getId(), request.getCourse().getCourseId());
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(true, "Enrollment Created", enrollment);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Create successful
        }catch  (RuntimeException e) {
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 Valid data
        }
    }
}
