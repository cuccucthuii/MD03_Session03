package com.example.session03.model.dto;

import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Student;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentEnrollmentResponse {
    private Integer enrollmentId;
    private Integer courseId;
    private String courseTitle;
    private Integer id;
    private String name;
}
