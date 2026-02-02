package com.example.session03.service.impl;

import com.example.session03.model.Instructor;
import com.example.session03.repository.InstructorRepository;
import com.example.session03.service.IInstructorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IInstructorServiceImpl implements IInstructorService {
    @Autowired
    private InstructorRepository instructorRepository;


    @Override
    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAllInstructors();
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return instructorRepository.findInstructorById(id).orElseThrow(() -> new RuntimeException("Instructor Not Found"));
    }

    @Override
    public Instructor createInstructor(Instructor request) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(request.getInstructorId());
        instructor.setInstructorName(request.getInstructorName());
        instructor.setInstructorEmail(request.getInstructorEmail());
        return instructorRepository.createInstructor(instructor);
    }

    @Override
    public Instructor updateInstructor(Instructor request, int id) {
        Instructor instructor = instructorRepository.findInstructorById(id).orElseThrow(() -> new RuntimeException("Instructor Not Found"));
        if (instructor == null) {
            return null;
        }
        instructor.setInstructorName(request.getInstructorName());
        instructor.setInstructorEmail(request.getInstructorEmail());
        return instructorRepository.updateInstructor(id, instructor);
    }

    @Override
    public Instructor deleteInstructor(Integer id) {
        Instructor instructor = instructorRepository.findInstructorById(id).orElseThrow(() -> new RuntimeException("Instructor Not Found"));
        if (instructor == null) {
            return null;
        }
        return instructorRepository.deleteInstructor(id);
    }
}
