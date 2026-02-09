package com.example.session03.repository;

import com.example.session03.exception.CourseNotFoundException;
import com.example.session03.model.dto.CourseResponse;
import com.example.session03.model.dto.CourseResponseV2;
import com.example.session03.model.entity.Course;
import com.example.session03.until.CourseStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

    @Query("SELECT c FROM Course c where c.courseStatus = :courseStatus")
    Page<Course> findAllByCourseStatus(@Param("courseStatus") CourseStatus courseStatus, Pageable pageable);

    @Query(" SELECT new com.example.session03.model.dto.CourseResponseV2(c.courseId, c.courseTitle, c.courseStatus) FROM Course c where c.courseStatus =:status")
    Page<CourseResponseV2> findAllByCourseStatusV2(@Param("status") CourseStatus courseStatus, Pageable pageable);

    @Query("select new com.example.session03.model.dto.CourseResponse(c.courseId, c.courseTitle, c.courseStatus, c.instructor.instructorName) from Course c where " +
            "(:status IS NULL OR c.courseStatus = :status) AND " +
            "(:keyword IS NULL OR LOWER(c.courseTitle) ilike LOWER(concat('%', :keyword, '%')) ) ")
    Page<CourseResponse> findAllByCourseStatusPage(@Param("status") CourseStatus courseStatus,@Param("keyword") String keyword , Pageable pageable);
}
