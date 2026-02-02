package com.example.session03.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Enrollment {
    //     (id, studentName, courseId)
    private Integer enrollmentId;
    private int courseId;
    private String studentName;
}
