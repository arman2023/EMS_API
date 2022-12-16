package com.sanket.ems.service.role.Exception;

public class RoleNotFoundException extends RuntimeException{
    private static String errorMessage = "Role does not exist.";

    public RoleNotFoundException() {
        super(errorMessage);
    }
}
