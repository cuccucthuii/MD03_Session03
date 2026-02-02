package com.example.session03.model.dto;

import com.example.session03.model.entity.Course;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollmentDetail {
    private Integer enrollmentId;
    private String studentName;
    private Course course;
}
