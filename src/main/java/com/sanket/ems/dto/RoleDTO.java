package com.sanket.ems.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {

    @Size(max = 45, message = "max siz is 45")
    private String roleName;

//    @Size(max = 45, message = "max siz is 45")
//    private String authority;

    @Size(max = 50, message = "max size is 50")
    private String description;

//    private Set<AuthorityDTO> authorityDTO;
}
