package com.example.session03.service.impl;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseUpdateRequest;
import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Instructor;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.InstructorRepository;
import com.example.session03.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Override
    public List<CourseResponse> findAllCourses() {
       return courseRepository.findAll()
               .stream().map(course -> new CourseResponse(
                       course.getCourseId(),
                       course.getCourseTitle(),
                       course.getCourseStatus(),
                       course.getInstructor().getInstructorName()
               )).toList();
    }

    @Override
    public CourseResponse findCourseById(Integer id) {
        return courseRepository.findById(id).map(
                c -> new CourseResponse(
                        c.getCourseId(),
                        c.getCourseTitle(),
                        c.getCourseStatus(),
                        c.getInstructor().getInstructorName()
                )
        ).orElseThrow(() -> new RuntimeException("Course Not Found"));
    }

    @Override
    public CourseResponse createCourse(CourseCreateRequest request) {

        Instructor instructor = instructorRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor Not Found"));

        Course course = new Course();
        course.setCourseTitle(request.getCourseTitle());
        course.setCourseStatus(request.getCourseStatus());
        course.setInstructor(instructor);
        courseRepository.save(course);
        return new CourseResponse(course.getCourseId(), course.getCourseTitle(), course.getCourseStatus(), course.getInstructor().getInstructorName());
    }

    @Override
    public CourseResponse updateCourse(CourseUpdateRequest request, int id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));

        Instructor instructor = instructorRepository.findById(request.getInstructorId())
                .orElseThrow(() -> new RuntimeException("Instructor Not Found"));
        course.setCourseTitle(request.getCourseTitle());
        course.setCourseStatus(request.getCourseStatus());
        course.setInstructor(instructor);
        courseRepository.save(course);
        return new CourseResponse(course.getCourseId(), course.getCourseTitle(), course.getCourseStatus(), course.getInstructor().getInstructorName());
    }

    @Override
    public void deleteCourse(Integer id) {
        Course course = courseRepository.findById(id).orElseThrow(() -> new RuntimeException("Course Not Found"));
        if (course == null) {
            throw new RuntimeException("Course Not Found");
        }
            courseRepository.delete(course);
    }
}
