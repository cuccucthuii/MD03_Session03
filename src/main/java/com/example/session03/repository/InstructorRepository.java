package com.example.session03.repository;

import com.example.session03.model.entity.Instructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InstructorRepository {
    private List<Instructor> instructorList = new ArrayList<>();

    public InstructorRepository() {
        instructorList.add(new Instructor(1, "Nguyen Van A", "a@gmail.com"));
        instructorList.add(new Instructor(2, "Nguyen Van B", "b@gmail.com"));
    }

    public List<Instructor> findAllInstructors() {
        return instructorList;
    }

    public Optional<Instructor> findInstructorById(Integer id) {
        Instructor instructor = instructorList.stream()
                .filter(i -> i.getInstructorId() == id)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Instructor not found"));
        return Optional.of(instructor);
    }

    public Instructor createInstructor(Instructor request) {
        instructorList.add(request);
        return request;
    }

    public Instructor updateInstructor(int id, Instructor request) {
        Instructor ins = findInstructorById(id).orElseThrow(() -> new RuntimeException("Instructor not found"));
        if (ins == null) {
            return null;
        }

        ins.setInstructorName(request.getInstructorName());
        ins.setInstructorEmail(request.getInstructorEmail());
        return ins;
    }

    public Instructor deleteInstructor(Integer id) {
        Instructor deleteInstructor = findInstructorById(id).orElseThrow(() -> new RuntimeException("Instructor Not found")); // Gọi hàm
        if (deleteInstructor != null) {
            instructorList.remove(deleteInstructor);
        }
        return deleteInstructor;
    }
}
