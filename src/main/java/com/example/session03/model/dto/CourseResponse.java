package com.example.session03.model.dto;

import com.example.session03.until.CourseStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Integer courseId;
    private String courseTitle;
    private CourseStatus courseStatus;
}
