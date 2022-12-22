package com.sanket.ems.service.employee;

import com.sanket.ems.dto.EmployeeDTO;
import com.sanket.ems.model.Employee;
import com.sanket.ems.service.department.DepartmentMapper;
import com.sanket.ems.service.role.RoleMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.stream.Collectors;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    default EmployeeDTO toDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setFirstName(employee.getFirstName());
        employeeDTO.setLastName(employee.getLastName());
        employeeDTO.setEmail(employee.getEmail());
        employeeDTO.setAddress(employee.getAddress());
        employeeDTO.setActive(employee.isActive());
        employeeDTO.setDepartment(DepartmentMapper.INSTANCE.toDTO(employee.getDepartmentFk()));
        employeeDTO.setRoles(employee.getRoles().stream().map(RoleMapper.INSTANCE::toDTO).collect(Collectors.toSet()));
        return employeeDTO;
    }

    @Mapping(target = "departmentFk", ignore = true)
    Employee toEntity(EmployeeDTO employeeDTO);

    static Employee toEntity(final EmployeeDTO employeeDTO, final Employee employee){
        employee.setFirstName(employeeDTO.getFirstName());
        employee.setLastName(employeeDTO.getLastName());
        employee.setEmail(employeeDTO.getEmail());
        employee.setAddress(employeeDTO.getAddress());
        employee.setActive(employeeDTO.isActive());
        return employee;
    }
}
