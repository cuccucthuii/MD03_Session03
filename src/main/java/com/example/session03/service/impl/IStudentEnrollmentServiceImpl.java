package com.example.session03.service.impl;

import com.example.session03.model.dto.StudentEnrollmentRequest;
import com.example.session03.model.dto.StudentEnrollmentResponse;
import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Student;
import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.StudentEnrollmentRepository;
import com.example.session03.repository.StudentRepository;
import com.example.session03.service.IStudentEnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IStudentEnrollmentServiceImpl implements IStudentEnrollmentService {
    private final StudentEnrollmentRepository studentEnrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public StudentEnrollmentResponse enrollStudent(StudentEnrollmentRequest request) {
        Student student = studentRepository.findById(request.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(request.getCourseId())
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment studentEnrollment = new StudentEnrollment();
        studentEnrollment.setStudent(student);
        studentEnrollment.setCourse(course);
        studentEnrollmentRepository.save(studentEnrollment);
        return new StudentEnrollmentResponse(
                studentEnrollment.getEnrollmentId(),
                studentEnrollment.getCourse().getCourseId(),
                studentEnrollment.getCourse().getCourseTitle(),
                studentEnrollment.getStudent().getId(),
                studentEnrollment.getStudent().getName()
        );
    }
}
