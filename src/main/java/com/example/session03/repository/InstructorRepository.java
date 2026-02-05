package com.example.session03.repository;

import com.example.session03.model.dto.InstructorResponse;
import com.example.session03.model.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository  extends JpaRepository<Instructor,Integer> {

    @Query("""
            select new com.example.session03.model.dto.InstructorResponse(
                         i.instructorId,
                         i.instructorName,
                         i.instructorEmail)
                         from Instructor i
                         where i.instructorId = :id""")
    Optional<InstructorResponse> getInstructorByInstructorId(@Param("id") Integer id);

    @Query("""
                 select new com.example.session03.model.dto.InstructorResponse(
                                                    i.instructorId,
                                                    i.instructorName,
                                                    i.instructorEmail)
                                                    from Instructor i
            """)
    List<InstructorResponse> findAllInstructorResponses();

}
