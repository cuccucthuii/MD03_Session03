package com.example.session03.repository;

import com.example.session03.model.dto.StudentResponse;
import com.example.session03.model.entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    @Query("select new com.example.session03.model.dto.StudentResponse(S.id, S.name, S.email) From Student S " +
            "where (LOWER(S.name) LIKE LOWER(concat('%', :keyword, '%')) )")
    Page<StudentResponse> getPagedStudentsByKeyword(@Param("keyword") String keyword, PageRequest pageRequest);
}
