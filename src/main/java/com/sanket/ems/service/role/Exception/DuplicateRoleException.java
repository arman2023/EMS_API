package com.sanket.ems.service.role.Exception;

public class DuplicateRoleException extends RuntimeException{
    private static String errorMessage = "Role already exist.";

    public DuplicateRoleException() {
        super(errorMessage);
    }
}
