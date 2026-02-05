package com.example.session03.model.dto;

import com.example.session03.model.entity.Course;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InstructorResponse {
    private Integer instructorId;
    private String instructorName;
    private String instructorEmail;
}
