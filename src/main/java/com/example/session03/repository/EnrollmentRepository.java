package com.example.session03.repository;

import com.example.session03.model.Enrollment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EnrollmentRepository {
    private List<Enrollment> enrollmentList = new ArrayList<>();

    public EnrollmentRepository() {
        enrollmentList.add(new Enrollment(1, 1, "Nguyen Van A"));
        enrollmentList.add(new Enrollment(2, 2, "Nguyen Van B"));
    }

    public List<Enrollment> findAllEnrollment() {
        return enrollmentList;
    }

    public Optional<Enrollment> getEnrollmentById(Integer id) {
        Enrollment enrollment = enrollmentList.stream()
                .filter(e -> e.getEnrollmentId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
        return Optional.of(enrollment);
    }

    public Enrollment createEnrollment(Enrollment request) {
        Enrollment enrollment = new Enrollment();
        enrollment.setEnrollmentId(request.getEnrollmentId());
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStudentName(request.getStudentName());
        enrollmentList.add(enrollment);
        return enrollment;
    }

    public Enrollment updateEnrollment(Enrollment request, Integer id) {
        Enrollment enrollment = getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found")); // Goi hàm đã khởi tạo
        if (enrollment == null) {
            return null;
        }
        enrollment.setCourseId(request.getCourseId());
        enrollment.setStudentName(request.getStudentName());
        return enrollment;
    }

    public Enrollment deleteEnrollment(Integer id) {
        Enrollment enrollment = getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found")); // Goi hàm đã khởi tạo
        if (enrollment != null) {
            enrollmentList.remove(enrollment);
        }
        return enrollment;
    }
}
