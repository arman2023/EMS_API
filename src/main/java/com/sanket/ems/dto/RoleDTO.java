package com.sanket.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @Size(max = 25, message = "max siz is 25")
    private String roleName;

    @Size(max = 50, message = "max size is 50")
    private String description;
}
