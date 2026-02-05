package com.example.session03.service;

import com.example.session03.model.dto.StudentEnrollmentRequest;
import com.example.session03.model.dto.StudentEnrollmentResponse;
import com.example.session03.model.entity.StudentEnrollment;

public interface IStudentEnrollmentService {
    StudentEnrollmentResponse enrollStudent(StudentEnrollmentRequest request);
}
