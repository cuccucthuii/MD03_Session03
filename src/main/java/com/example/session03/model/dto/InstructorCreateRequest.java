package com.example.session03.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InstructorCreateRequest {
    private String instructorName;
    private String instructorEmail;
}
