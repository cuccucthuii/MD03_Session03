package com.example.session03.model.dto;

import com.example.session03.model.entity.Instructor;
import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.until.CourseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseCreateRequest {
    private String courseTitle;
    private CourseStatus courseStatus;
    private Integer instructorId;
}
