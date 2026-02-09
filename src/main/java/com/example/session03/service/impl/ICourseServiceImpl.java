package com.example.session03.service.impl;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseResponseV2;
import com.example.session03.model.dto.CourseUpdateRequest;
import com.example.session03.model.entity.Course;
import com.example.session03.model.entity.Instructor;
import com.example.session03.repository.CourseRepository;
import com.example.session03.repository.InstructorRepository;
import com.example.session03.service.ICourseService;
import com.example.session03.until.CourseStatus;
import com.example.session03.until.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;

import java.util.List;

@Service
public class ICourseServiceImpl implements ICourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private InstructorRepository instructorRepository;
    @Autowired
    private JsonMapper.Builder builder;

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

    @Override
    public PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, String direction) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(sortDirection, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Course> responsePage = courseRepository.findAll(pageRequest);
        Page<CourseResponse> courseResponses = responsePage.map(course -> new CourseResponse(
                course.getCourseId(),
                course.getCourseTitle(),
                course.getCourseStatus(),
                course.getInstructor().getInstructorName()
        ));

        PageResponse<CourseResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(courseResponses.getContent());
        pageResponse.setPage(responsePage.getNumber());
        pageResponse.setSize(responsePage.getSize());
        pageResponse.setTotalItems((int) responsePage.getTotalElements());
        pageResponse.setTotalPages(responsePage.getTotalPages());
        pageResponse.setLast(responsePage.isLast());

        return pageResponse;
    }

    @Override
    public PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, String direction, CourseStatus status) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(sortDirection, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<Course> responsePage = courseRepository.findAllByCourseStatus(status, pageRequest);
        Page<CourseResponse> courseResponses = responsePage.map(course -> new CourseResponse(
                course.getCourseId(),
                course.getCourseTitle(),
                course.getCourseStatus(),
                course.getInstructor().getInstructorName()
        ));

        PageResponse<CourseResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(courseResponses.getContent());
        pageResponse.setPage(responsePage.getNumber());
        pageResponse.setSize(responsePage.getSize());
        pageResponse.setTotalItems((int) responsePage.getTotalElements());
        pageResponse.setTotalPages(responsePage.getTotalPages());
        pageResponse.setLast(responsePage.isLast());
        return pageResponse;
    }

    @Override
    public PageResponse<CourseResponseV2> getPagedCoursesByStatusV2(int page, int size, String sortBy, String direction, CourseStatus status) {
        Sort.Direction sortDirection = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(sortDirection, sortBy);
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<CourseResponseV2> responsePage = courseRepository.findAllByCourseStatusV2(status, pageRequest);
        Page<CourseResponseV2> courseResponses = responsePage.map(course -> new CourseResponseV2(
                course.getCourseId(),
                course.getCourseTitle(),
                course.getCourseStatus()
        ));

        PageResponse<CourseResponseV2> pageResponse = new PageResponse<>();
        pageResponse.setItems(courseResponses.getContent());
        pageResponse.setPage(responsePage.getNumber());
        pageResponse.setSize(responsePage.getSize());
        pageResponse.setTotalItems((int) responsePage.getTotalElements());
        pageResponse.setTotalPages(responsePage.getTotalPages());
        pageResponse.setLast(responsePage.isLast());
        return pageResponse;
    }

    @Override
    public PageResponse<CourseResponse> getPagedCourse(int page, int size, String sortBy, String direction, CourseStatus status, String keyword) {
        // Sort -- Unsort neu thieu direction ( direction == null )
        Sort sort = Sort.unsorted();
        if (sortBy != null && direction != null) {
            sort = Sort.by(Sort.Direction.fromString(direction), sortBy);
        }
        PageRequest pageRequest = PageRequest.of(page, size, sort);
        // Khi maf status / keyword null thi tang repo se su ly logic
        Page<CourseResponse> responsePage = courseRepository.findAllByCourseStatusPage(status, keyword, pageRequest);

        // Map sang DTO
        Page<CourseResponse> courseResponses = responsePage.map(course -> new CourseResponse(
                course.getCourseId(),
                course.getCourseTitle(),
                course.getCourseStatus(),
                course.getInstructorName()
        ));

        // Map sang Page Response
        PageResponse<CourseResponse> pageResponse = new PageResponse<>();
        pageResponse.setItems(courseResponses.getContent());
        pageResponse.setPage(responsePage.getNumber());
        pageResponse.setSize(responsePage.getSize());
        pageResponse.setTotalItems((int) responsePage.getTotalElements());
        pageResponse.setTotalPages(responsePage.getTotalPages());
        pageResponse.setLast(responsePage.isLast());
        return pageResponse;
    }
}
