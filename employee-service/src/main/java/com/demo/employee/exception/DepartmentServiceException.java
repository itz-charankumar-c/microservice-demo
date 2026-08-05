package com.demo.employee.exception;

/** Thrown when the department-service is unreachable or returns an error. */
public class DepartmentServiceException extends RuntimeException {
    public DepartmentServiceException(String message) {
        super(message);
    }
}

