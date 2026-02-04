package com.example.session03.service;

import com.example.session03.model.dto.EnrollCourseRequest;
import com.example.session03.model.dto.EnrollmentDetail;
import com.example.session03.model.entity.StudentEnrollment;

import java.util.List;

public interface IEnrollmentService {
    List<StudentEnrollment> findAllEnrollment();

    StudentEnrollment findEnrollmentById(Integer id);

    StudentEnrollment createEnrollment(StudentEnrollment request);

    StudentEnrollment updateEnrollment(StudentEnrollment request, Integer id);

    StudentEnrollment deleteEnrollment(Integer id);

    EnrollmentDetail createEnrollmentDetail(EnrollCourseRequest request);
}
