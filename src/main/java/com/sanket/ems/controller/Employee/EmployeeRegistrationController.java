package com.sanket.ems.controller.Employee;

import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.service.employee.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/ems/signup")
public class EmployeeRegistrationController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.saveEmployee(employeeDTO);
        return new ResponseEntity(employeeDTO1, HttpStatus.CREATED);
    }
}
