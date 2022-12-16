package com.sanket.ems.service.department.Exception;

public class DuplicateDepartmentException extends RuntimeException{
    private static String errorMessage = "Department already exist.";

    public DuplicateDepartmentException() {
        super(errorMessage);
    }
}
