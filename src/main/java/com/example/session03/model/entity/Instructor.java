package com.example.session03.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer instructorId;
    @Column(nullable = false, length = 100)
    private String instructorName;
    @Column(nullable = false, unique = true)
    private String instructorEmail;

    // Join
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor") // tham chieu den ten cua Join trong Course
    private List<Course> courses;
}
