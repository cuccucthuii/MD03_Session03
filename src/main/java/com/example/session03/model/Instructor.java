package com.example.session03.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Instructor {
    private Integer instructorId;
    private String instructorName;
    private String instructorEmail;
}
