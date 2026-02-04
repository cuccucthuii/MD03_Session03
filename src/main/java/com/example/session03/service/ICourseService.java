package com.example.session03.service;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseUpdateRequest;
import com.example.session03.model.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> findAllCourses();

    Course findCourseById(Integer id);

    Course createCourse(CourseCreateRequest request);

    Course updateCourse(CourseUpdateRequest request, int id);

    Course deleteCourse(Integer id);
}
