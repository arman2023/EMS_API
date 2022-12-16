package com.sanket.ems.service.department;

import com.sanket.ems.dao.DepartmentRepository;
import com.sanket.ems.dto.DepartmentDTO;
import com.sanket.ems.model.Department;
import com.sanket.ems.service.department.Exception.DepartmentNotFoundException;
import com.sanket.ems.service.department.Exception.DuplicateDepartmentException;
import com.sanket.ems.service.employee.Exception.DuplicateEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Transactional
    public DepartmentDTO saveDepartment(DepartmentDTO departmentDTO){
        validateDepartment(departmentDTO);
        Department department = DepartmentMapper.INSTANCE.toEntity(departmentDTO);
        Department newDepartment = departmentRepository.save(department);
        return DepartmentMapper.INSTANCE.toDTO(newDepartment);
    }

    @Transactional
    public DepartmentDTO updateDepartment(DepartmentDTO departmentDTO){
        Department department = getDepartmentById(departmentDTO.getDepartmentName());
        Department updatedDepartment = DepartmentMapper.toEntity(departmentDTO, department);
        return DepartmentMapper.INSTANCE.toDTO(updatedDepartment);
    }

    @Transactional
    public void deleteDepartment(DepartmentDTO departmentDTO){
        Department department = getDepartmentById(departmentDTO.getDepartmentName());
        departmentRepository.delete(department);
    }

    public List<DepartmentDTO> getDepartmentList(){
        List<Department> departments = departmentRepository.findAll();
        List<DepartmentDTO> departmentDTOS = departments.stream().map(DepartmentMapper.INSTANCE::toDTO).collect(Collectors.toList());
        return departmentDTOS;
    }

    public Department getDepartmentById(String departmentName) {
        return departmentRepository.findById(departmentName).orElseThrow(() -> new DepartmentNotFoundException());
    }

    public void validateDepartment(DepartmentDTO departmentDTO){
        final boolean isDepartmentExist = departmentRepository.findById(departmentDTO.getDepartmentName()).isPresent();
        if(isDepartmentExist)
            throw new DuplicateDepartmentException();
    }
}
