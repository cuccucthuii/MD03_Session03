package com.example.session03.repository;

import com.example.session03.model.dto.EnrollCourseRequest;
import com.example.session03.model.entity.StudentEnrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentEnrollmentRepository extends JpaRepository<StudentEnrollment, Integer> {






//    private List<StudentEnrollment> enrollmentList = new ArrayList<>();
//
//    public EnrollmentRepository() {
////        enrollmentList.add(new StudentEnrollment(1, 1, "Nguyen Van A"));
////        enrollmentList.add(new StudentEnrollment(2, 2, "Nguyen Van B"));
//    }
//
//    public List<StudentEnrollment> findAllEnrollment() {
//        return enrollmentList;
//    }
//
//    public Optional<StudentEnrollment> getEnrollmentById(Integer id) {
//        StudentEnrollment enrollment = enrollmentList.stream()
//                .filter(e -> e.getEnrollmentId() == id)
//                .findFirst()
//                .orElseThrow(() -> new RuntimeException("Enrollment Not Found"));
//        return Optional.of(enrollment);
//    }
//
//    public StudentEnrollment createEnrollment(StudentEnrollment request) {
//        StudentEnrollment enrollment = new StudentEnrollment();
//        enrollment.setEnrollmentId(request.getEnrollmentId());
////        enrollment.setCourseId(request.getCourseId());
////        enrollment.setStudentName(request.getStudentName());
//        enrollmentList.add(enrollment);
//        return enrollment;
//    }
//
//    public StudentEnrollment updateEnrollment(StudentEnrollment request, Integer id) {
//        StudentEnrollment enrollment = getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found")); // Goi hàm đã khởi tạo
//        if (enrollment == null) {
//            return null;
//        }
////        enrollment.setCourseId(request.getCourseId());
////        enrollment.setStudentName(request.getStudentName());
//        return enrollment;
//    }
//
//    public StudentEnrollment deleteEnrollment(Integer id) {
//        StudentEnrollment enrollment = getEnrollmentById(id).orElseThrow(() -> new RuntimeException("Enrollment Not Found")); // Goi hàm đã khởi tạo
//        if (enrollment != null) {
//            enrollmentList.remove(enrollment);
//        }
//        return enrollment;
//    }
//
//    public StudentEnrollment createEnrollCourseRequest(EnrollCourseRequest request) {
//        StudentEnrollment enrollment = new StudentEnrollment();
//        enrollment.setEnrollmentId(request.getId());
////        enrollment.setCourseId(request.getCourseId());
////        enrollment.setStudentName(request.getStudentName());
//        enrollmentList.add(enrollment);
//        return enrollment;
//    }
}
