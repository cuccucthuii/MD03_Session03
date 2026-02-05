package com.example.session03.service;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseUpdateRequest;
import com.example.session03.model.entity.Course;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> findAllCourses();

    CourseResponse findCourseById(Integer id);

    CourseResponse createCourse(CourseCreateRequest request);

    CourseResponse updateCourse(CourseUpdateRequest request, int id);

    void deleteCourse(Integer id);
}
