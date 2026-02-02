package com.example.session03.service.impl;

import com.example.session03.exception.CourseNotActiveException;
import com.example.session03.exception.CourseNotFoundException;
import com.example.session03.model.dto.EnrollCourseRequest;
import com.example.session03.model.dto.EnrollmentDetail;
import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Enrollment;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.EnrollmentRepository;
import com.example.session03.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEnrollmentServiceImpl implements IEnrollmentService {
    @Autowired
    private EnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;


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

    @Override
    public EnrollmentDetail createEnrollmentDetail(EnrollCourseRequest request) {
        // Kiểm tra course có tồn tại không
        Course course = courseRepository.findCourseById(request.getCourseId()).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));

        // Kiểm tra course có đang active không
        if (!"active".equalsIgnoreCase(course.getCourseStatus())) {
            throw new CourseNotActiveException("Course Status Not Active");
        }
        // Request -> Entity
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(request.getId());
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStudentName(request.getStudentName());

        // Lưu lại
        Enrollment saved = enrollmentRepository.createEnrollment(enrollment);

        // Entity -> Response
        EnrollmentDetail enrollmentDetail = new EnrollmentDetail();
        enrollmentDetail.setEnrollmentId(saved.getEnrollmentId());
        enrollmentDetail.setStudentName(saved.getStudentName());
        enrollmentDetail.setCourse(course);
        return enrollmentDetail;
    }

}
