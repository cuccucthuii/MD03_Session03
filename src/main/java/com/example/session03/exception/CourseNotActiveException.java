package com.example.session03.exception;

public class CourseNotActiveException extends RuntimeException {
    public CourseNotActiveException(String message) {
        super(message);
    }
}
