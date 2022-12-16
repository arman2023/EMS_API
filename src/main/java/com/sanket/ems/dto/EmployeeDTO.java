package com.sanket.ems.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class EmployeeDTO {

    private Integer employeeId;

    @Size(max = 45, message = "max size is 45")
    private String firstName;

    @Size(max = 45, message = "max size is 45")
    private String lastName;

    @Email(message = "Should be a valid email address")
    private String email;

    @Size(max = 45, message = "max size is 45")
    private String password;

    @Size(max = 100, message = "max size is 100")
    private String address;

    @NotNull
    private DepartmentDTO department;
}
