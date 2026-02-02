package com.example.session03.service;

import com.example.session03.model.Enrollment;

import java.util.List;

public interface IEnrollmentService {
    List<Enrollment> findAllEnrollment();

    Enrollment findEnrollmentById(Integer id);

    Enrollment createEnrollment(Enrollment request);

    Enrollment updateEnrollment(Enrollment request, Integer id);

    Enrollment deleteEnrollment(Integer id);
}
