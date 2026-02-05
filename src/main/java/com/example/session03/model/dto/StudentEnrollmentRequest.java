package com.example.session03.model.dto;

import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentEnrollmentRequest {
    private Integer enrollmentId;
    private Integer courseId;
    private Integer studentId;
}
