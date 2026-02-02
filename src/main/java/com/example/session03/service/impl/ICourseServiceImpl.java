package com.example.session03.service.impl;

import com.example.session03.model.Course;
import com.example.session03.repository.CourseRepository;
import com.example.session03.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<Course> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course findCourseById(Integer id) {
        return courseRepository.findCourseById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
    }

    @Override
    public Course createCourse(Course request) {
        Course course = new Course();
        course.setCourseId(request.getCourseId());
        course.setCourseTitle(request.getCourseTitle());
        course.setCourseStatus(request.getCourseStatus());
        course.setInstructorId(request.getInstructorId());
        return courseRepository.createCourse(course);
    }

    @Override
    public Course updateCourse(Course request, int id) {
        Course course = courseRepository.findCourseById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
        if (course == null) {
            return null;
        }
        course.setCourseTitle(request.getCourseTitle());
        course.setCourseStatus(request.getCourseStatus());
        course.setInstructorId(request.getInstructorId());
        return courseRepository.updateCourse(course, id);
    }

    @Override
    public Course deleteCourse(Integer id) {
        Course course = courseRepository.findCourseById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
        if (course != null) {
            courseRepository.deleteCourse(id);
        }
        return course;
    }
}
