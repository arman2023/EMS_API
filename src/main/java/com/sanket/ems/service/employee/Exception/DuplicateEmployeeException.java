package com.sanket.ems.service.employee.Exception;

public class DuplicateEmployeeException extends RuntimeException {
    private static String errorMessage = "Employee already exist.";

    public DuplicateEmployeeException() {
        super(errorMessage);
    }
}
