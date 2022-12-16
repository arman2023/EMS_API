package com.sanket.ems.service.department.Exception;

public class DepartmentNotFoundException extends RuntimeException{
    private static String errorMessage = "Department does not exist.";

    public DepartmentNotFoundException() {
        super(errorMessage);
    }
}
