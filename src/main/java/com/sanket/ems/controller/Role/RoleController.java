package com.sanket.ems.controller.Role;

import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.dto.RoleDTO;
import com.sanket.ems.model.Role;
import com.sanket.ems.service.employee.EmployeeService;
import com.sanket.ems.service.role.RoleMapper;
import com.sanket.ems.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/{roleName}")
    public RoleDTO getRoleById(@PathVariable final String roleName){
        Role role = roleService.getRoleById(roleName);
        return RoleMapper.INSTANCE.toDTO(role);
    }

    @GetMapping("/list")
    public List<RoleDTO> getRoles(){
        return roleService.getRoles();
    }

    @PostMapping("")
    public ResponseEntity<RoleDTO> saveRole(@RequestBody @Valid final RoleDTO roleDTO){
       RoleDTO newRole = roleService.saveRole(roleDTO);
       return new ResponseEntity(newRole, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<RoleDTO> updateRole(@RequestBody @Valid final RoleDTO roleDTO){
        RoleDTO updatedRole = roleService.updateRole(roleDTO);
        return new ResponseEntity(updatedRole, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteRole(@RequestBody @Valid final RoleDTO roleDTO){
        roleService.deleteRole(roleDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/assignRole/{roleName}")
    public ResponseEntity<Void> assignRole(@PathVariable final String roleName,
                                           @RequestBody @Valid final EmployeeDTO employeeDTO){
        employeeService.assignRole(roleName, employeeDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/unAssignRole/{roleName}")
    public ResponseEntity<Void> unAssignRole(@PathVariable final String roleName,
                                             @RequestBody @Valid final EmployeeDTO employeeDTO){
        employeeService.unAssignRole(roleName, employeeDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
