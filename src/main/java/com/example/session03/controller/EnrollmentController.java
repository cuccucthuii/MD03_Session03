package com.example.session03.controller;

import com.example.session03.exception.CourseNotActiveException;
import com.example.session03.exception.CourseNotFoundException;
import com.example.session03.model.dto.EnrollCourseRequest;
import com.example.session03.model.dto.EnrollmentDetail;
import com.example.session03.model.entity.StudentEnrollment;
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
    public ResponseEntity<ApiResponse<List<StudentEnrollment>>> getAllEnrollment() {
        List<StudentEnrollment> enrollments = enrollmentService.findAllEnrollment();
        ApiResponse<List<StudentEnrollment>> response = new ApiResponse<>(true, "Find All Enrollment Data", enrollments);
        return new ResponseEntity<>(response, HttpStatus.OK); // 200 / if null = [] not error
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> getEnrollmentById(@PathVariable Integer id) {
        try {
            StudentEnrollment enrollment = enrollmentService.findEnrollmentById(id);
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(true, "Enrollment Found", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200 successful
        } catch (RuntimeException e) {
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404 not found
        }
        /**
         * Dùng Try/Catch để bắt ngoại lệ -> Nếu không tồn tại = Lỗi!
         */
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StudentEnrollment>> createEnrollment(@RequestBody StudentEnrollment request) {
        try {
            StudentEnrollment enrollment = enrollmentService.createEnrollment(request);
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(true, "Enrollment Created", enrollment);
            return new ResponseEntity<>(response, HttpStatus.CREATED); // 201 Create successful
        }catch  (RuntimeException e) {
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 400 Valid data
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> updateEnrollment(@PathVariable Integer id, @RequestBody StudentEnrollment request) {
        try {
            StudentEnrollment enrollment = enrollmentService.findEnrollmentById(id);
            enrollment = enrollmentService.updateEnrollment(request, id);
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(true, "Enrollment Updated", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK); // 200
        }catch (RuntimeException e) {
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 ID NOT FOUND
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<StudentEnrollment>> deleteEnrollment(@PathVariable Integer id) {
        try {
            StudentEnrollment enrollment = enrollmentService.findEnrollmentById(id);
            enrollment = enrollmentService.deleteEnrollment(id);
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(true, "Enrollment Deleted", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RuntimeException e) {
            ApiResponse<StudentEnrollment> response = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 ID NOT FOUND
        }
    }

    @PostMapping("/enroll-course")
    public ResponseEntity<ApiResponse<EnrollmentDetail>> createEnrollmentDetail(@RequestBody EnrollCourseRequest request) {
        try {
            EnrollmentDetail enrollment = enrollmentService.createEnrollmentDetail(request);
            ApiResponse<EnrollmentDetail> response = new ApiResponse<>(true, "Enrollment Detail Created", enrollment);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (CourseNotFoundException ex) {
            ApiResponse<EnrollmentDetail> response = new ApiResponse<>(false, "Course Not Found", null);
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); // 404
        }catch (CourseNotActiveException ex){
            ApiResponse<EnrollmentDetail> response = new ApiResponse<>(false, "Course Not Active", null);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); // 404
        }catch (RuntimeException e){
            ApiResponse<EnrollmentDetail> responseFalse = new ApiResponse<>(false, "Enrollment Not Found", null);
            return new ResponseEntity<>(responseFalse, HttpStatus.BAD_REQUEST); //400
        }
    }
}
