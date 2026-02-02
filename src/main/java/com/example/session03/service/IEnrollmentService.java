package com.example.session03.service;

import com.example.session03.model.dto.EnrollCourseRequest;
import com.example.session03.model.dto.EnrollmentDetail;
import com.example.session03.model.entity.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> findAllEnrollment();

    Enrollment findEnrollmentById(Integer id);

    Enrollment createEnrollment(Enrollment request);

    Enrollment updateEnrollment(Enrollment request, Integer id);

    Enrollment deleteEnrollment(Integer id);

    EnrollmentDetail createEnrollmentDetail(EnrollCourseRequest request);
}
