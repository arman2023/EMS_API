package com.sanket.ems.controller.Employee;

import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.dto.RoleDTO;
import com.sanket.ems.model.Employee;
import com.sanket.ems.service.employee.EmployeeMapper;
import com.sanket.ems.service.employee.EmployeeService;
import com.sanket.ems.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    public EmployeeDTO getEmployeeByID(@PathVariable("employeeId") final Integer employeeId){
        Employee employee = employeeService.getEmployeeById(employeeId);
        return EmployeeMapper.INSTANCE.toDTO(employee);
    }

    @GetMapping("/list")
    public List<EmployeeDTO> getEmployeeList(){
        return employeeService.getEmployeeList();
    }

    @PutMapping("")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        EmployeeDTO employeeDTO1 = employeeService.updateEmployee(employeeDTO);
        return new ResponseEntity(employeeDTO1, HttpStatus.OK);
    }
    @DeleteMapping("")
    public ResponseEntity<Void> deleteEmployee(@RequestBody @Valid EmployeeDTO employeeDTO){
        employeeService.deleteEmployee(employeeDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
