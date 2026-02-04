package com.example.session03.service.impl;

import com.example.session03.model.dto.InstructorCreateRequest;
import com.example.session03.model.entity.Instructor;
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
        return instructorRepository.findAll();
    }

    @Override
    public Instructor findInstructorById(Integer id) {
        return instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor Not Found"));
    }

    @Override
    public Instructor createInstructor(Instructor request) {
        Instructor instructor = new Instructor();
        instructor.setInstructorId(request.getInstructorId());
        instructor.setInstructorName(request.getInstructorName());
        instructor.setInstructorEmail(request.getInstructorEmail());
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor updateInstructor(Instructor request, int id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor Not Found"));
        if (instructor == null) {
            return null;
        }
        instructor.setInstructorName(request.getInstructorName());
        instructor.setInstructorEmail(request.getInstructorEmail());
        return instructorRepository.save(instructor);
    }

    @Override
    public Instructor deleteInstructor(Integer id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(() -> new RuntimeException("Instructor Not Found"));
        if (instructor != null) {
        instructorRepository.delete(instructor);
        }
        return instructor;
    }

    @Override
    public Instructor createInstructorDTO(InstructorCreateRequest request) {
        Instructor instructor = new Instructor();
        instructor.setInstructorName(request.getInstructorName());
        instructor.setInstructorEmail(request.getInstructorEmail());
        instructorRepository.save(instructor);
        return instructor;
    }
}
