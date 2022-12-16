package com.sanket.ems.controller.Department;

import com.sanket.ems.dto.DepartmentDTO;
import com.sanket.ems.model.Department;
import com.sanket.ems.service.department.DepartmentMapper;
import com.sanket.ems.service.department.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/{departmentName}")
    public DepartmentDTO getDepartmentById(@PathVariable final String departmentName){
        Department department = departmentService.getDepartmentById(departmentName);
        return DepartmentMapper.INSTANCE.toDTO(department);
    }

    @GetMapping("/list")
    public List<DepartmentDTO> getDepartmentList(){
        return departmentService.getDepartmentList();
    }

    @PostMapping("")
    public ResponseEntity<DepartmentDTO> saveDepartment(@RequestBody @Valid final DepartmentDTO departmentDTO){
        DepartmentDTO departmentDTO1 = departmentService.saveDepartment(departmentDTO);
        return new ResponseEntity(departmentDTO1, HttpStatus.CREATED);
    }

    @PutMapping("")
    public ResponseEntity<DepartmentDTO> updateDepartment(@RequestBody @Valid final DepartmentDTO departmentDTO){
        DepartmentDTO departmentDTO1 = departmentService.updateDepartment(departmentDTO);
        return new ResponseEntity(departmentDTO1, HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<Void> deleteDepartment(@RequestBody @Valid final DepartmentDTO departmentDTO){
       departmentService.deleteDepartment(departmentDTO);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
