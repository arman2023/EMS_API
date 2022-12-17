package com.sanket.ems.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class LoginDTO {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
