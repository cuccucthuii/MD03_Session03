package com.example.session03.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Course {
    //(id, title, status, instructorId)
    private Integer courseId;
    private String courseTitle;
    private String courseStatus;
    private int instructorId;
}
