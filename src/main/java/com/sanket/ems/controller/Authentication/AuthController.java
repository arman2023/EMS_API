package com.sanket.ems.controller.Authentication;

import com.sanket.ems.config.JWTGenerator;
import com.sanket.ems.dto.AuthResponseDTO;
import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.dto.LoginDTO;
import com.sanket.ems.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ems/auth")
public class AuthController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JWTGenerator jwtGenerator;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody @Valid final LoginDTO loginDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUserName(), loginDTO.getPassword())
        );
         SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity(employeeDTO1, HttpStatus.CREATED);
    }
}
