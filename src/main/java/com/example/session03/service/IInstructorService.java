package com.example.session03.service;

import com.example.session03.model.dto.InstructorCreateRequest;
import com.example.session03.model.dto.InstructorResponse;
import com.example.session03.model.entity.Instructor;

import java.util.List;

public interface IInstructorService {
    List<InstructorResponse> findAllInstructors();

    InstructorResponse findInstructorById(Integer id);
    //API POST DTO
    InstructorResponse createInstructorDTO(InstructorCreateRequest request);

    Instructor createInstructor(Instructor request);

    Instructor updateInstructor(Instructor request, int id);

    Instructor deleteInstructor(Integer id);


}
