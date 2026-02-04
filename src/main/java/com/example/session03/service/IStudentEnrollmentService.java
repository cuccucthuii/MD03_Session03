package com.example.session03.service;

import com.example.session03.model.entity.StudentEnrollment;

public interface IStudentEnrollmentService {
    StudentEnrollment enrollStudent(Integer studentId, Integer courseId);
}
