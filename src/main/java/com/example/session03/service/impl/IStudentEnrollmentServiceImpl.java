package com.example.session03.service.impl;

import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Student;
import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.StudentEnrollmentRepository;
import com.example.session03.repository.StudentRepository;
import com.example.session03.service.IStudentEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IStudentEnrollmentServiceImpl implements IStudentEnrollmentService {
    @Autowired
    private StudentEnrollmentRepository studentEnrollmentRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public StudentEnrollment enrollStudent(Integer studentId, Integer courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        StudentEnrollment studentEnrollment = new StudentEnrollment();
        studentEnrollment.setStudent(student);
        studentEnrollment.setCourse(course);
        studentEnrollmentRepository.save(studentEnrollment);
        return studentEnrollment;
    }
}
