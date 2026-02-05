package com.example.session03.controller;

import com.example.session03.model.dto.InstructorCreateRequest;
import com.example.session03.model.dto.InstructorResponse;
import com.example.session03.model.entity.Instructor;
import com.example.session03.service.IInstructorService;
import com.example.session03.until.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    @Autowired
    private IInstructorService instructorService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<InstructorResponse>>> findAllInstructors() {
        List<InstructorResponse> instructor = instructorService.findAllInstructors();
        ApiResponse<List<InstructorResponse>> response = new ApiResponse<>(true, "Instructor List", instructor);
        return new ResponseEntity<>(response, HttpStatus.OK); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<InstructorResponse>> findInstructorById(@PathVariable Integer id) {
        try {
            InstructorResponse instructor = instructorService.findInstructorById(id);
            ApiResponse<InstructorResponse> response = new ApiResponse<>(true, "Instructor Found", instructor);
            return new ResponseEntity<>(response, HttpStatus.OK); //200
        } catch (RuntimeException e) {
            ApiResponse<InstructorResponse> falseResponse = new ApiResponse<>(false, "Instructor Not Found", null);
            return new ResponseEntity<>(falseResponse, HttpStatus.NOT_FOUND); // 404
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Instructor>> createInstructor(@RequestBody Instructor request) {
        try {
            Instructor instructor = instructorService.createInstructor(request);
            ApiResponse<Instructor> response = new ApiResponse<>(true, "Instructor Created", instructor);
            return new ResponseEntity<>(response, HttpStatus.CREATED); //201
        } catch (RuntimeException e) {
            ApiResponse<Instructor> falseResponse = new ApiResponse<>(false, "Instructor Not Found", null);
            return new ResponseEntity<>(falseResponse, HttpStatus.BAD_REQUEST); // 400
        }
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<ApiResponse<Instructor>> updateInstructor(@PathVariable Integer id, @RequestBody Instructor request) {
//        try {
//            Instructor instructor = instructorService.findInstructorById(id);
//            Instructor updatedInstructor = instructorService.updateInstructor(request, id);
//            ApiResponse<Instructor> response = new ApiResponse<>(true, "Instructor Updated", updatedInstructor);
//            return new ResponseEntity<>(response, HttpStatus.OK); // 200
//        } catch (RuntimeException e) {
//            ApiResponse<Instructor> falseResponse = new ApiResponse<>(false, "Instructor Not Found", null);
//            return new ResponseEntity<>(falseResponse, HttpStatus.NOT_FOUND); // 404
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse<Instructor>> deleteInstructor(@PathVariable Integer id) {
//        try {
//            Instructor instructor = instructorService.findInstructorById(id);
//            instructor = instructorService.deleteInstructor(id);
//            ApiResponse<Instructor> response = new ApiResponse<>(true, "Instructor Deleted", instructor);
//            return new ResponseEntity<>(response, HttpStatus.OK); // 200
//        } catch (RuntimeException e) {
//            ApiResponse<Instructor> falseResponse = new ApiResponse<>(false, "Instructor NotFound", null);
//            return new ResponseEntity<>(falseResponse, HttpStatus.NOT_FOUND); //404
//        }
//
//    }

    // API DTO SESSION 04
    @PostMapping("/dto")
    public ResponseEntity<ApiResponse<InstructorResponse>> createInstructor(@RequestBody InstructorCreateRequest request) {
        try {
            InstructorResponse instructor = instructorService.createInstructorDTO(request);
            ApiResponse<InstructorResponse> response = new ApiResponse<>(true, "Instructor Created", instructor);
            return new ResponseEntity<>(response, HttpStatus.CREATED); //201
        } catch (RuntimeException e) {
            ApiResponse<InstructorResponse> falseResponse = new ApiResponse<>(false, "Instructor Not Found", null);
            return new ResponseEntity<>(falseResponse, HttpStatus.BAD_REQUEST); // 400
        }
    }
}
