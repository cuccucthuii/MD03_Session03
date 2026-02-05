package com.example.session03.repository;

import com.example.session03.exception.CourseNotFoundException;
import com.example.session03.model.entity.Course;
import com.example.session03.until.CourseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
