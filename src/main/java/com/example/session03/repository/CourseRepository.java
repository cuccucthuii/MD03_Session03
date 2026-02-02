package com.example.session03.repository;

import com.example.session03.exception.CourseNotFoundException;
import com.example.session03.model.entity.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository {
    private List<Course> list = new ArrayList<>();

    public CourseRepository() {
        list.add(new Course(1, "Java", "Acctive", 1));
        list.add(new Course(2, "C#", "Inacctive", 2));
    }

    public List<Course> findAllCourses() {
        return list;
    }

    public Optional<Course> findCourseById(int id) {
        Course course = list.stream()
                .filter(c -> c.getCourseId() == id)
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException("Course Not Found"));
        return Optional.of(course); // Đảm bảo value không null
    }

    public Course createCourse(Course course) {
        list.add(course);
        return course;
    }

    public Course updateCourse(Course request, int id) {
        Course course = findCourseById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
        if (course == null) {
            return null;
        }
        course.setCourseTitle(request.getCourseTitle());
        course.setCourseStatus(request.getCourseStatus());
        course.setInstructorId(request.getInstructorId());
        return course; // Đảm bảo khi update thì các giá trị mới không null
    }

    public Course deleteCourse(int id) {
        Course course = findCourseById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
        if (course != null) {
            list.remove(course);
        }
        return course;
    }


}
