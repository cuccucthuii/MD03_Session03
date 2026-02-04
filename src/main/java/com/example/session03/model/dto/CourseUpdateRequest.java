package com.example.session03.model.dto;

import com.example.session03.until.CourseStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseUpdateRequest {
    private String courseTitle;
    private CourseStatus courseStatus;
    private Integer instructorId;
}
