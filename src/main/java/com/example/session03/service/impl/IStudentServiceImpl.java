package com.example.session03.service.impl;

import com.example.session03.model.entity.Student;
import com.example.session03.model.entity.StudentEnrollment;
import com.example.session03.repository.StudentRepository;
import com.example.session03.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
}
