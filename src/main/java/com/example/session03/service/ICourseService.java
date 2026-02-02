package com.example.session03.service;

import com.example.session03.model.entity.Course;

import java.util.List;

public interface ICourseService {
    List<Course> findAllCourses();

    Course findCourseById(Integer id);

    Course createCourse(Course request);

    Course updateCourse(Course request, int id);

    Course deleteCourse(Integer id);
}
