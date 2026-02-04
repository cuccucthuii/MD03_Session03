package com.example.session03.service.impl;

import com.example.session03.exception.CourseNotActiveException;
import com.example.session03.exception.CourseNotFoundException;
import com.example.session03.model.dto.EnrollCourseRequest;
import com.example.session03.model.dto.EnrollmentDetail;
import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.StudentEnrollmentRepository;
import com.example.session03.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IEnrollmentServiceImpl implements IEnrollmentService {
    @Autowired
    private StudentEnrollmentRepository enrollmentRepository;
    @Autowired
    private CourseRepository courseRepository;


    @Override
    public List<StudentEnrollment> findAllEnrollment() {
        return enrollmentRepository.findAll();
    }

    @Override
    public StudentEnrollment findEnrollmentById(Integer id) {
        return enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
    }

    @Override
    public StudentEnrollment createEnrollment(StudentEnrollment request) {
        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setEnrollmentId(request.getEnrollmentId());
        enrollment.setCourse(request.getCourse());
        enrollment.setStudent(request.getStudent());
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public StudentEnrollment updateEnrollment(StudentEnrollment request, Integer id) {
        StudentEnrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
        if (enrollment == null) {
            return null;
        }
        enrollment.setCourse(request.getCourse());
        enrollment.setStudent(request.getStudent());
        enrollmentRepository.save(request);
        return enrollment;
    }

    @Override
    public StudentEnrollment deleteEnrollment(Integer id) {
        StudentEnrollment enrollment = enrollmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
        if (enrollment == null) {
            return null;
        }
        enrollmentRepository.delete(enrollment);
        return enrollment;
    }

    @Override
    public EnrollmentDetail createEnrollmentDetail(EnrollCourseRequest request) {
        // Kiểm tra course có tồn tại không
        Course course = courseRepository.findById(request.getCourseId()).orElseThrow(() -> new CourseNotFoundException("Course Not Found"));

        // Kiểm tra course có đang active không
        if (!"active".equalsIgnoreCase(String.valueOf(course.getCourseStatus()))) {
            throw new CourseNotActiveException("Course Status Not Active");
        }
        // Request -> Entity
        StudentEnrollment enrollment = new StudentEnrollment();
        enrollment.setEnrollmentId(request.getId());
//        enrollment.setCourseId(request.getCourseId());
//        enrollment.setStudentName(request.getStudentName());

        // Lưu lại
        StudentEnrollment saved = enrollmentRepository.save(enrollment);

        // Entity -> Response
        EnrollmentDetail enrollmentDetail = new EnrollmentDetail();
        enrollmentDetail.setEnrollmentId(saved.getEnrollmentId());
//        enrollmentDetail.setStudentName(saved.getStudentName());
        enrollmentDetail.setCourse(course);
        return enrollmentDetail;
    }

}
