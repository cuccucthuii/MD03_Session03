package com.example.session03.service;

import com.example.session03.model.dto.CourseCreateRequest;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseResponseV2;
import com.example.session03.model.dto.CourseUpdateRequest;
import com.example.session03.model.entity.Course;
import com.example.session03.until.CourseStatus;
import com.example.session03.until.PageResponse;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ICourseService {
    List<CourseResponse> findAllCourses();

    CourseResponse findCourseById(Integer id);

    CourseResponse createCourse(CourseCreateRequest request);

    CourseResponse updateCourse(CourseUpdateRequest request, int id);

    void deleteCourse(Integer id);

    // Sap xep va phan trang
    PageResponse<CourseResponse> getPagedCourses(int page, int size, String sortBy, String direction);

    PageResponse<CourseResponse> getPagedCoursesByStatus(int page, int size, String sortBy, String direction, CourseStatus status);

    // Gioi 02 - Toi uu cau lenh
    PageResponse<CourseResponseV2> getPagedCoursesByStatusV2(int page, int size, String sortBy, String direction, CourseStatus status);

    // Suat xac 01 - Xu ly du lieu tang Repo
    PageResponse<CourseResponse> getPagedCourse(int page, int size, String sortBy, String direction, CourseStatus status, String keyword);
}
