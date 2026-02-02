package com.example.session03.service.impl;

import com.example.session03.model.Enrollment;
import com.example.session03.repository.EnrollmentRepository;
import com.example.session03.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEnrollmentServiceImpl implements IEnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;


    @Override
    public List<Enrollment> findAllEnrollment() {
        return enrollmentRepository.findAllEnrollment();
    }

    @Override
    public Enrollment findEnrollmentById(Integer id) {
        return enrollmentRepository.getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
    }

    @Override
    public Enrollment createEnrollment(Enrollment request) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(request.getEnrollmentId());
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStudentName(request.getStudentName());
        return enrollmentRepository.createEnrollment(enrollment);
    }

    @Override
    public Enrollment updateEnrollment(Enrollment request, Integer id) {
        Enrollment enrollment = enrollmentRepository.getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
        if (enrollment == null) {
            return null;
        }
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStudentName(request.getStudentName());
        enrollmentRepository.updateEnrollment(request, id);
        return enrollment;
    }

    @Override
    public Enrollment deleteEnrollment(Integer id) {
        Enrollment enrollment = enrollmentRepository.getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
        if (enrollment == null) {
            return null;
        }
        return enrollmentRepository.deleteEnrollment(id);
    }
}
