package com.example.session03.service.impl;

import com.example.session03.model.dto.StudentResponse;
import com.example.session03.model.entity.Student;
import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.repository.StudentRepository;
import com.example.session03.service.IStudentService;
import com.example.session03.until.ApiResponse;
import com.example.session03.until.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class IStudentServiceImpl implements IStudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        Student addStudent = new Student();
        addStudent.setName(student.getName());
        addStudent.setEmail(student.getEmail());
        studentRepository.save(addStudent);
        return addStudent;
    }

    @Override
    public PageResponse<StudentResponse> getPagedStudentsByKeyword(int page, int size, String sortBy, Sort.Direction direction, String keyword) {
        Sort sort = Sort.unsorted();
        if (sortBy != null && direction != null) {
            sort = Sort.by(direction, sortBy);
        }
        if (keyword == null) {
            keyword = "";
        }

        PageRequest pageRequest = PageRequest.of(page, size, sort);

        // call repo
        Page<StudentResponse> responsePage = studentRepository.getPagedStudentsByKeyword(keyword, pageRequest);

        // Map
        Page<StudentResponse> studentResponses = responsePage.map(studentResponse -> new StudentResponse(
                studentResponse.getId(),
                studentResponse.getName(),
                studentResponse.getEmail()
        ));

        // Convert Response API
        PageResponse<StudentResponse> response = new PageResponse<>();
        response.setItems(studentResponses.getContent());
        response.setPage(responsePage.getNumber());
        response.setSize(responsePage.getSize());
        response.setTotalPages(responsePage.getTotalPages());
        response.setTotalItems((int) responsePage.getTotalElements());
        response.setLast(responsePage.isLast());

        return response;
    }
}
