package com.example.session03.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollCourseRequest {
    private Integer id;
    private String studentName;
    private Integer courseId;
}
