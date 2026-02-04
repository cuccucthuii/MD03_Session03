package com.example.session03.model.entity;

import com.example.session03.until.CourseStatus;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "courses")
public class Course {
    //(id, title, status, instructorId)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer courseId;
    @Column(nullable = false)
    private String courseTitle;
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    // Join column
    @ManyToOne(fetch = FetchType.LAZY) // Chi goi khi can
    @JoinColumn(name = "instructor_id") // Tham chieu den ID Instructor
    private Instructor instructorId;

    @JsonManagedReference
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "course") // private Course course;
    private List<StudentEnrollment> enrollments;
}
