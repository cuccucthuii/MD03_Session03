package com.example.session03.controller;

import com.example.session03.model.Enrollment;
import com.example.session03.service.IEnrollmentService;
import com.example.session03.until.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/enrollments")
public class EnrollmentController {
    @Autowired
    private IEnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Enrollment>>> getAllEnrollment() {
        List<Enrollment> enrollments = enrollmentService.findAllEnrollment();
        ApiResponse<List<Enrollment>> response = new ApiResponse<>(true, "Find All Enrollment Data", enrollments);
        return new ResponseEntity<>(response, HttpStatus.OK); // 200 / if null = [] not error
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> getEnrollmentById(@PathVariable Integer id) {
        try {
            Enrollment enrollment = enrollmentService.findEnrollmentById(id);
            ApiResponse<Enrollment> response = new ApiResponse<>(true, "Enrollment Found", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 successful
        } catch (RuntimeException e) {
            ApiResponse<Enrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 not found
        }
        /**
         * Dùng Try/Catch để bắt ngoại lệ -> Nếu không tồn tại = Lỗi!
         */
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Enrollment>> createEnrollment(@RequestBody Enrollment request) {
        try {
            Enrollment enrollment = enrollmentService.createEnrollment(request);
            ApiResponse<Enrollment> response = new ApiResponse<>(true, "Enrollment Created", enrollment);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Create successful
        }catch  (RuntimeException e) {
            ApiResponse<Enrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 Valid data
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> updateEnrollment(@PathVariable Integer id, @RequestBody Enrollment request) {
        try {
            Enrollment enrollment = enrollmentService.findEnrollmentById(id);
            enrollment = enrollmentService.updateEnrollment(request, id);
            ApiResponse<Enrollment> response = new ApiResponse<>(true, "Enrollment Updated", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200
        }catch (RuntimeException e) {
            ApiResponse<Enrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 ID NOT FOUND
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Enrollment>> deleteEnrollment(@PathVariable Integer id) {
        try {
            Enrollment enrollment = enrollmentService.findEnrollmentById(id);
            enrollment = enrollmentService.deleteEnrollment(id);
            ApiResponse<Enrollment> response = new ApiResponse<>(true, "Enrollment Deleted", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RuntimeException e) {
            ApiResponse<Enrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 ID NOT FOUND
        }
    }
}
