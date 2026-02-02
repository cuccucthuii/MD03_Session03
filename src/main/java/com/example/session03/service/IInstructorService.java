package com.example.session03.service;

import com.example.session03.model.Instructor;

import java.util.List;

public interface IInstructorService {
    List<Instructor> findAllInstructors();

    Instructor findInstructorById(Integer id);

    Instructor createInstructor(Instructor request);

    Instructor updateInstructor(Instructor request, int id);

    Instructor deleteInstructor(Integer id);
}
