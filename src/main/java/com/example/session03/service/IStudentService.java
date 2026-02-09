package com.example.session03.service;

import com.example.session03.model.dto.StudentResponse;
import com.example.session03.model.entity.Student;
import com.example.session03.until.PageResponse;
import org.hibernate.query.SortDirection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

public interface IStudentService {
    Student createStudent(Student student);

    PageResponse<StudentResponse> getPagedStudentsByKeyword(int page, int size, String sortBy, Sort.Direction direction, String keyword);
}
