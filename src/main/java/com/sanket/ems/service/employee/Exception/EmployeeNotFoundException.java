package com.sanket.ems.service.employee.Exception;

public class EmployeeNotFoundException extends RuntimeException{
    private static String errorMessage = "Employee does not exist.";

    public EmployeeNotFoundException() {
        super(errorMessage);
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
