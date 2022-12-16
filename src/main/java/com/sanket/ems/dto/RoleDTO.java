package com.sanket.ems.dto;

import lombok.Data;

import javax.validation.constraints.Size;

@Data
public class RoleDTO {

    @Size(max = 25, message = "max siz is 25")
    private String roleName;

    @Size(max = 50, message = "max size is 50")
    private String description;

}
