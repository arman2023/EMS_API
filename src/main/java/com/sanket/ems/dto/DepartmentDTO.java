package com.sanket.ems.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class DepartmentDTO {

    @Size(max = 45, message = "max size is 45")
    private String departmentName;

    @Size(max = 100, message = "max size is 100")
    private String description;
}
